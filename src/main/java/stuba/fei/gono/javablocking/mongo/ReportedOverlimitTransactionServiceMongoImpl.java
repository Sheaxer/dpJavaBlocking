package stuba.fei.gono.javablocking.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import stuba.fei.gono.javablocking.errors.ReportedOverlimitTransactionException;
import stuba.fei.gono.javablocking.mongo.data.repositories.ReportedOverlimitTransactionRepository;
import stuba.fei.gono.javablocking.pojo.ReportedOverlimitTransaction;
import stuba.fei.gono.javablocking.pojo.State;
import stuba.fei.gono.javablocking.services.ReportedOverlimitTransactionService;

import java.time.OffsetDateTime;
import java.util.Optional;
@Service
public class ReportedOverlimitTransactionServiceMongoImpl implements ReportedOverlimitTransactionService {

    @Value("${reportedOverlimitTransaction.transaction.sequenceName:customSequences}")
    private String sequenceName;
    //private ClientRepository clientRepository;
    private ReportedOverlimitTransactionRepository transactionRepository;
    private NextSequenceService nextSequenceService;

    @Autowired
    public ReportedOverlimitTransactionServiceMongoImpl(ReportedOverlimitTransactionRepository transactionRepository,
                                                        NextSequenceService nextSequenceService)
    {
        //this.clientRepository = clientRepository;
        this.transactionRepository = transactionRepository;
        this.nextSequenceService= nextSequenceService;
    }

    @Override
    public ReportedOverlimitTransaction postTransaction(ReportedOverlimitTransaction newTransaction) {

        String newId = nextSequenceService.getNewId(transactionRepository,sequenceName);


        return putTransaction(newId,newTransaction);
    }

    @Override
    public ReportedOverlimitTransaction getTransactionById(String id) throws ReportedOverlimitTransactionException {
        Optional<ReportedOverlimitTransaction> transaction= transactionRepository.findById(id);
        if(transaction.isPresent())
            return transaction.get();
        else
            throw new ReportedOverlimitTransactionException("ID_NOT_FOUND");
    }

    @Override
    public ReportedOverlimitTransaction putTransaction(String id, ReportedOverlimitTransaction transaction) {
        transaction.setId(id);
        transaction.setModificationDate(OffsetDateTime.now());
        transaction.setZoneOffset(transaction.getModificationDate().getOffset().getId());
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public ReportedOverlimitTransaction deleteTransaction(String id) throws ReportedOverlimitTransactionException {
        Optional<ReportedOverlimitTransaction> transaction= transactionRepository.findById(id);
        if(transaction.isPresent())
        {
            ReportedOverlimitTransaction trans = transaction.get();
            if(!trans.getState().equals(State.CLOSED))
            {
                transactionRepository.delete(trans);
                return  trans;
            }
            else
                throw new ReportedOverlimitTransactionException("STATE_CLOSED");

        }
        else
        {
            throw new ReportedOverlimitTransactionException("ID_NOT_FOUND");
        }
    }
}

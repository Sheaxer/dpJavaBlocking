package stuba.fei.gono.javablocking.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stuba.fei.gono.javablocking.config.NextSequenceService;
import stuba.fei.gono.javablocking.data.ClientRepository;
import stuba.fei.gono.javablocking.data.ReportedOverlimitTransactionRepository;
import stuba.fei.gono.javablocking.errors.CreateReportedOverlimitTransactionException;
import stuba.fei.gono.javablocking.errors.ReportedOverlimitTransactionException;
import stuba.fei.gono.javablocking.pojo.ReportedOverlimitTransaction;
import stuba.fei.gono.javablocking.pojo.State;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/reportedOverlimitTransaction")
@Slf4j
public class TransactionController {
    @Value("${reportedOverlimitTransaction.transaction.sequenceName:customSequences}")
    private String sequenceName;
    private ClientRepository clientRepository;
    private ReportedOverlimitTransactionRepository transactionRepository;
    private NextSequenceService nextSequenceService;


    @Autowired
    public TransactionController(ClientRepository clientRepository, ReportedOverlimitTransactionRepository transactionRepository, NextSequenceService nextSequenceService)
    {
        this.clientRepository = clientRepository;
        this.transactionRepository = transactionRepository;
        this.nextSequenceService = nextSequenceService;
    }

    /***
     * Returns single ReportedOverlimitTransaction based on its  id.
     * @see ReportedOverlimitTransaction
     * @param id Id of the requested ReportedOverlimitTransaction
     * @return requested instance of ReportedOverlimitTransaction
     * @throws ReportedOverlimitTransactionException exception if there is no instance of ReportedOverlimitTransaction with the requested id
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ReportedOverlimitTransaction> getTransaction(@PathVariable String id) throws ReportedOverlimitTransactionException
    {
        Optional<ReportedOverlimitTransaction> transaction= transactionRepository.findById(id);
        if(transaction.isPresent())
        {
            ReportedOverlimitTransaction trans = transaction.get();

            /*trans.setState(State.CREATED);
            transactionRepository.save(trans);*/

            //trans.getModificationDate();
            /*if(trans.getZoneOffset() != null) {
                ZoneOffset offset = ZoneOffset.of(trans.getZoneOffset());
                trans.setModificationDate(trans.getModificationDate().toInstant().atOffset(offset));
            }*/
            //log.info(trans.getClientId().getFirstName());
            return new ResponseEntity<>(trans,HttpStatus.OK);
        }
        else
        {
           throw new ReportedOverlimitTransactionException("ID_NOT_FOUND");
        }
    }

    @PostMapping
    public ResponseEntity<ReportedOverlimitTransaction> postTransaction(@Valid @RequestBody ReportedOverlimitTransaction newTransaction)
            throws CreateReportedOverlimitTransactionException
    {
        // validate date
        /*boolean wasModified = false;
        String newId = nextSequenceService.getNextSequence("customSequences");
        while(transactionRepository.findById(newId).isPresent())
        {
            newId = nextSequenceService.getNextSequence("customSequences");
            wasModified=true;
            log.info("wasModified");
        }
        if(wasModified) {
            nextSequenceService.setNextSequence("customSequences", newId);
        }*/
        log.info(this.sequenceName);
        String newId = nextSequenceService.getNewId(transactionRepository,sequenceName);

        newTransaction.setId(newId);


        if(newTransaction.getState() == null)
            newTransaction.setState(State.CREATED);
        newTransaction.setModificationDate(OffsetDateTime.now());
        newTransaction.setZoneOffset(newTransaction.getModificationDate().getOffset().getId());
        transactionRepository.save(newTransaction);
        log.info("Created new transaction " + newTransaction.getId());
        return new ResponseEntity<>(newTransaction,HttpStatus.CREATED);
    }

    /***
     * Deletes non-closed ReportedOverlimitTransaction with the request id from database.
     * @see ReportedOverlimitTransaction
     * @see State
     * @param id id of ReportedOverlimitTransaction that should be deleted
     * @return deleted ReportedOverlimitTransaction
     * @throws ReportedOverlimitTransactionException if requested ReportedOverlimitTransaction cannot be deleted
     * either because it's state is not CLOSED or there is isn't one with requested it stored.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ReportedOverlimitTransaction> deleteTransaction(@PathVariable String id)
    {
        Optional<ReportedOverlimitTransaction> transaction= transactionRepository.findById(id);
        if(transaction.isPresent())
        {
            ReportedOverlimitTransaction trans = transaction.get();
            if(!trans.getState().equals(State.CLOSED))
            {
                transactionRepository.delete(trans);
                return new ResponseEntity<>(trans,HttpStatus.OK);
            }
            else
                throw new ReportedOverlimitTransactionException("STATE_CLOSED");

        }
        else
        {
            throw new ReportedOverlimitTransactionException("ID_NOT_FOUND");
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReportedOverlimitTransaction> putTransaction(@PathVariable String id, @Valid @RequestBody ReportedOverlimitTransaction transaction)
    {
        //Optional<ReportedOverlimitTransaction> tran= transactionRepository.findById(id);
        transaction.setId(id);
        transaction.setModificationDate(OffsetDateTime.now());
        transaction.setZoneOffset(transaction.getModificationDate().getOffset().getId());
        transactionRepository.save(transaction);
        return new ResponseEntity<>(transaction, HttpStatus.OK );
    }

}

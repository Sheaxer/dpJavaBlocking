package stuba.fei.gono.javablocking.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stuba.fei.gono.javablocking.NextSequenceService;
import stuba.fei.gono.javablocking.data.ClientRepository;
import stuba.fei.gono.javablocking.data.ReportedOverlimitTransactionRepository;
import stuba.fei.gono.javablocking.errors.CreateReportedOverlimitTransactionException;
import stuba.fei.gono.javablocking.errors.ReportedOverlimitTransactionException;
import stuba.fei.gono.javablocking.pojo.ReportedOverlimitTransaction;
import stuba.fei.gono.javablocking.pojo.State;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/reportedOverlimitTransaction")
public class TransactionController {

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReportedOverlimitTransaction> getTransaction(@PathVariable String id) throws ReportedOverlimitTransactionException
    {
        Optional<ReportedOverlimitTransaction> transaction= transactionRepository.findById(id);
        if(transaction.isPresent())
        {
            return new ResponseEntity<>(transaction.get(),HttpStatus.ACCEPTED);
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

        newTransaction.setId(nextSequenceService.getNextSequence("customSequences"));
        if(newTransaction.getState() == null)
            newTransaction.setState(State.CREATED);
        newTransaction.setModificationDate(ZonedDateTime.now());
        transactionRepository.save(newTransaction);
        return new ResponseEntity<>(newTransaction,HttpStatus.CREATED);
    }

}

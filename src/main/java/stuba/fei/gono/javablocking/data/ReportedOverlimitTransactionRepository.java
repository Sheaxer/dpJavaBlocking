package stuba.fei.gono.javablocking.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import stuba.fei.gono.javablocking.pojo.ReportedOverlimitTransaction;

import java.util.Optional;

public interface ReportedOverlimitTransactionRepository extends MongoRepository<ReportedOverlimitTransaction, String>
{

    /*@Override
    Optional<ReportedOverlimitTransactionRepository> findById(String s);

    @Override
    void delete(ReportedOverlimitTransactionRepository reportedOverlimitTransactionRepository);

    @Override
    <S extends ReportedOverlimitTransactionRepository> S save(S s);*/
}

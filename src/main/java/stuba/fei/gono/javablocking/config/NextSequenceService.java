package stuba.fei.gono.javablocking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import stuba.fei.gono.javablocking.pojo.SequenceId;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class NextSequenceService {
    private final MongoOperations mongoOperations;

    @Autowired
    public NextSequenceService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public String getNextSequence(String seqName)
    {
        SequenceId counter = mongoOperations.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq",1),
                options().returnNew(true).upsert(true),
                SequenceId.class);

        return String.valueOf( counter.getSeq());
    }
}

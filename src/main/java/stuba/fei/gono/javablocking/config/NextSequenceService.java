package stuba.fei.gono.javablocking.config;

import com.mongodb.client.MongoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import stuba.fei.gono.javablocking.pojo.SequenceId;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/***
 * Class that allows to perform operations on MongoDB
 */
@Service
@Slf4j
public class NextSequenceService {
    private final MongoOperations mongoOperations;

    @Autowired
    public NextSequenceService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    /***
     * Finds the sequence, gets id that will be used to insert new Document into MongoDB and updates the sequence
     * to generate new id.
     * @param seqName name of the sequence where to find next value of id
     * @return new id to be used to insert new Document into MongoDB
     */
    public String getNextSequence(String seqName)
    {
        SequenceId counter = mongoOperations.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq",1),
                options().returnNew(true).upsert(true),
                SequenceId.class);
        if(counter == null)
        {
            setNextSequence(seqName,String.valueOf(1));
            return "1";
        }
        /*if(counter == null)
        {
            log.info("doing new");
            mongoOperations.executeCommand("{db.sequence.insert({_id: \""+seqName+"\",seq: 2})}");
            return "1";
        }*/

        return String.valueOf( counter.getSeq());
    }

    /***
     * Sets value of sequence in MongoDB.
     * @param seqName name of the sequence
     * @param value value that the sequence will be set to
     */
    public void setNextSequence(String seqName,String value)
    {
        SequenceId s = mongoOperations.findAndModify(
                query(where("_id").is(seqName)),
                new Update().set("seq",Long.valueOf(value)),
                options().returnNew(true).upsert(true),
                SequenceId.class
        );
        /*if(s==null)
        {
            mongoOperations.executeCommand("{db.sequence.insert({_id: \""+seqName+"\",seq: "+value+"})}");
        }*/
    }

    public String getNewId(MongoRepository<?,String> rep,String sequenceName)
    {
        boolean wasModified = false;
        String newId = this.getNextSequence(sequenceName);
        while( rep.findById(newId).isPresent())
        {
            newId = this.getNextSequence(sequenceName);
            wasModified=true;
            log.info("wasModified");
        }
        if(wasModified) {
            this.setNextSequence(sequenceName, newId);
        }
        return newId;
    }
}

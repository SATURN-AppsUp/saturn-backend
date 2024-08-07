package com.example.saturn.services;

import com.example.saturn.models.CustomSequences;
import com.example.saturn.models.enums.DomainCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class GenIdService {
    @Autowired
    private MongoOperations mongo;

    public int genNextId(String seqName){
        CustomSequences counter = mongo.findAndModify(
                query(where("_id").is(seqName)),new Update().inc("seq",1),
                options().returnNew(true).upsert(true),
                CustomSequences.class
        );
        return counter.getSeq();
    }

    public String genNextCode(String code) {
        var id = getCurrentId(code.toString());
        id+=1;
        var finalCode = code + "-"+id;
        return finalCode;
    }

    public int getCurrentId(String seqName){
        CustomSequences counter = mongo.findOne(
                query(where("_id").is(seqName)),
                CustomSequences.class
        );
        if (counter == null) {
            return genNextId(seqName);
        }
        return counter.getSeq();
    }
}

package com.ishan.rd.beorg;

public class TempWork {
      /*ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.valueToTree(medicalRecordReqDTO);
        String issuesJsonStr = objectMapper.writeValueAsString(
                ((ObjectNode)rootNode).remove("issues"));

        String medicalRecordJsonStr = objectMapper.writeValueAsString(rootNode);
        log.info(medicalRecordJsonStr);
        log.info(issuesJsonStr);*/

        /*medicalRecord.setTitle("title");
        VPack vPack = new VPack.Builder().build();
        vPack.serialize(medicalRecord);*/

    // template.collection("", new CollectionCreateOptions().keyOptions())
       /* final TransactionOptions options = new TransactionOptions()
                .writeCollections(C_MED_ISSUES, C_MED_RECORD, E_HAVING_ISSUE).
                        readCollections(C_MED_ISSUES, C_MED_RECORD)
                .params(issuesJsonStr);*/
       /* String action = "function (params) { "
                + "var db = require('internal').db;"
                + "let medDoc = db." + C_MED_RECORD + ".save("+medicalRecordJsonStr+");"*/
    //  + "let issDoc = db." + C_MED_ISSUES + ".save("+issuesJsonStr+");"
    //  + "return issDoc"
    //  + "`Insert {_from : ${medDoc._id} , _to : ${issDoc._id} } in "+ E_HAVING_ISSUE+"`"
    // + "db._query(`FOR y in issDoc "
    //+ "`Insert {_from : ${medDoc._id} , _to : y._id } into "+ E_HAVING_ISSUE+"`"
    //  + "db." + E_HAVING_ISSUE + ".save({_from :"
    //   +  "${medDoc._id}, _to : y._id}));`"
    //  + "}";

    // String res  = db.transaction(action, String.class,options);
    //log.info(res);
    // db.

}

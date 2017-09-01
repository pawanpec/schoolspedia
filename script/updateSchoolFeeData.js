var sp_fee_data=db.getCollection('sp_fee_data').find({"id" : 137297});
sp_fee_data.forEach(function(doc) {
    var node=db.getCollection('fields_current.node').findOne({_id:doc.id});
 //   print(node.title);
    try {
        node.PS_SEATS=doc["nursery admission seat"];
        node.PP_SEATS=doc["preprimary seat"];
        node.FEES=doc["tution fees"];
        node.sd.E=doc["Email"];
        node.sd.W=doc["website"];
        node.sd.PHONE_NO=doc["Mobile"];
        node.pt=doc["Page Title"];
        longi=doc["longitude"];
        lat=doc["latitude"];
        c = [parseFloat(longi),parseFloat(lat)];
       t = "Point";
       var l={"coordinates":c,"type":t};
        node.loc=l;
        print(node);
       db.getCollection('fields_current.node').save(node);
}catch(err) {
print(err);
}
});
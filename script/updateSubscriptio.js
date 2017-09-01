var d = new Date();
var s = new Date();
s.setDate(s.getDate() -1);
var et = d.getTime()/1000;
var st = s.getTime()/1000;
print("start "+st);
print("end date "+et);
var admiss_ap=db.getCollection('admission_application').find({ct:{$gte:st,$lt:et}});
admiss_ap.forEach(function(doc) { 
   var email=doc.email
 //   print(node.title);
    try {
       var subscription={"e":email,"s":"s",src:"web",p:"askquery"};
       
       print(subscription);
       //db.getCollection('subscription').save(subscription);
}catch(err) {
print(err);
}
});
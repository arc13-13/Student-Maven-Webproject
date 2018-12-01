drop table stu;
delete from stu;
CREATE TABLE `stu` (
  `fname` varchar(40) NOT NULL,
  `lname` varchar(40) NOT NULL,
  `roll` varchar(40) NOT NULL,
  `dob` varchar(20) NOT NULL ,
  `pmarks` int(11) NOT NULL,
  `cmarks` int(11) NOT NULL,
  `mmarks` int(11) NOT NULL,
  `grade` varchar(5) NOT NULL,
  `path` longtext DEFAULT NULL,
  `gender` varchar(30) NOT NULL,
  `pass` varchar(40) NOT NULL,
  PRIMARY KEY (`roll`)
) 


insert into stu values('archit','sanghvi','021',"08-06-1996",20,20,20,"B","C:\\Users\\admin\\Desktop\\workspace\\Student\\WebContent\\images\\ebay.jpg","Male","a");
insert into stu values('archit','sanghvi','022',"08-06-1996",20,20,20,"B","C:\\Users\\admin\\Desktop\\workspace\\Student\\WebContent\\images\\ebay.jpg","Male","b");
insert into stu values('archit','sanghvi','023',"08-06-1996",20,20,20,"B","C:\\Users\\admin\\Desktop\\workspace\\Student\\WebContent\\images\\ebay.jpg","Male","c");
insert into stu values('archit','sanghvi','024',"08-06-1996",20,20,20,"B","C:\\Users\\admin\\Desktop\\workspace\\Student\\WebContent\\images\\ebay.jpg","Male","d");
insert into stu values('archit','sanghvi','025',"08-06-1996",20,20,20,"B","C:\\Users\\admin\\Desktop\\workspace\\Student\\WebContent\\images\\ebay.jpg","Male","e");
insert into stu values('archit','sanghvi','041',"08-06-1996",20,20,20,"B","C:\\Users\\admin\\Desktop\\workspace\\Student\\WebContent\\images\\ebay.jpg","Male","f");


select * from stu;
SELECT fname,lname,roll,dob,pmarks,cmarks,mmarks,grade,path FROM stu
package com.project.constants;

public class QueryConstants {

public static final String LOGIN_QUERY =
"select count(User_id) from user_registration where User_id=? and User_password=?";

public static final String USERID_PASS_SEQUENCE_GENERATOR =
"select userid_pass_seq.nextval from dual";

public static final String USERID_VISA_SEQUENCE_GENERATOR =
"select userid_visa_seq.nextval from dual";

public static final String REGISTER_QUERY =
"insert into user_registration values (?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?)";

public static final String PASSPORTID_30PAGE_SEQUENCE_GENERATOR =
"select passport_30page_seq.nextval from dual";

public static final String PASSPORTID_60PAGE_SEQUENCE_GENERATOR =
"select passport_60page_seq.nextval from dual";

public static final String EXISTING_PASSPORT_QUERY = 
"select count(Passport_Id) from Passport_Details where Login_Id =? and Application_Status='Applied'";

public static final String APPLY_PASSPORT_QUERY =
"insert into Passport_Details values(?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,?)";

public static final String PASSPORT_VERIFICATION_QUERY = 
"select Passport_Id from Passport_Details where Login_Id =? and Application_Status='Applied'";

public static final String STUDENT_VISAID_SEQUENCE_GENERATOR =
"select student_visaId_seq.nextval from dual";

public static final String PRIVATE_EMPLOYEE_VISAID_SEQUENCE_GENERATOR =
"select privateEmployee_visaId_seq.nextval from dual";

public static final String GOVERNMENT_EMPLOYEE_VISAID_SEQUENCE_GENERATOR = 
"select governmentEmployee_visaId_seq.nextval from dual";

public static final String SELF_EMPLOYEE_VISAID_SEQUENCE_GENERATOR = 
"select selfEmployee_visaId_seq.nextval from dual";

public static final String RETIRED_EMPLOYEE_VISAID_SEQUENCE_GENERATOR =
"select retiredEmployee_visaId_seq.nextval from dual";

public static final String APPLY_VISA_QUERY = 
"insert into Visa_Detail values(?,?,?,to_date(?,'yyyy-MM-dd'),to_date(?,'yyyy-MM-dd'),to_date(?,'yyyy-MM-dd'),?,?,?,?,?)";
	
public static final String UPDATE_PASSPORT_QUERY=
"update Passport_Details set Passport_id=?,Remarks=?,Country=?,State=?,City=?,Pin=?,Type_of_service=?,Booklet_type=?,Issue_date=?,Expiry_date=?,Passport_amount=?,Application_status=? where Login_id=? and Application_status='Applied' ";

public static final String EXPIRE_PASSPORT_QUERY=
"update Passport_Details set Remarks=?, Application_status = ? where Login_id=? and Application_status='Applied' ";

public static final String VISA_USER_VALIDATION_QUERY = 
"select count(User_id) from user_registration where User_id=? and Hint_Q=? and Hint_A=?";

public static final String VISA_CANCELLATION_QUERY =
"update Visa_Detail set Cancellation_amount=?,Remarks=? where Login_id=? and  Visa_id =? and Remarks='Active'";

public static final String FETCH_VISA_QUERY = 
"select Occupation,to_char(Date_Of_Issue,'yyyy-MM-dd'),to_char(Date_Of_Expiry,'yyyy-MM-dd'),Visa_amount from Visa_Detail where Login_id=?" ;

public static final String CHECK_VISA_QUERY =
"select count(Visa_id) from Visa_Detail where Login_id=? and Passport_id=? and Remarks='Active'";

public static final String FETCH_PASSPORT_QUERY=
"select to_char(Expiry_Date,'yyyy-MM-dd'),Passport_Id from Passport_Details where Login_Id =? and Application_Status='Applied'";

public static final String PASSPORT_VISA_CANCELLATION_QUERY =
"update Visa_Detail set Remarks=? where Login_id=? and Passport_id=? and Remarks='Active'";

}

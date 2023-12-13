package mypackages;


public class App {
    public static void main(String[] args) {
        // authenticate
        String auth_url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
        String postMethod = "POST";
        String body = "{'login_id' : 'test@sunbasedata.com','password' :'Test@123'}";
        String accessToken = Authenticate.apiCall(auth_url, postMethod, body);
        System.out.println(accessToken);
        
        // get customer
        String api_url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";
        String getMethod = "GET";
        String getParams = "?cmd=get_customer_list";
        String customers = GetCustomers.getCustomer(api_url, accessToken, getMethod, getParams);
        System.out.println(customers);

        // create new customer
        String createParams = "?cmd=create";
        String createHeader = "{'first_name': 'Jane', 'last_name': 'Doe', 'street': 'Elvnu Street', 'address': 'H no 2' ,'city': 'Delhi', 'state': 'Delhi', 'email': 'sam@gmail.com', 'phone': '12345678'}";
        CreateCustomer.createCustomer(api_url, postMethod, accessToken, createParams, createHeader);

        // create new customer
        String deleteParams = "?cmd=delete&uuid=";
        String uuid = "testdd230fcab1be4542a971c5406d548b88";
        DeleteCustomer.deleteCustomer(api_url, postMethod, accessToken, deleteParams, uuid);
        
        // update new customer
        String updateParams = "?cmd=update&uuid=";
        String updateuuid = "test94aea3bd24434b9982d91a2978e8f010";
        String updateHeader = "{'first_name': 'Jane', 'last_name': 'Doe', 'street': 'Elvnu Street', 'address': 'H no 2' ,'city': 'Delhi', 'state': 'Delhi', 'email': 'sam@gmail.com', 'phone': '12345678'}";
        UpdateCustomer.updateCustomer(api_url, postMethod, accessToken, updateParams, updateHeader, updateuuid);
    }
}


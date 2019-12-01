
package Controller;

import DAOs.PaymentDAO;
import Models.Payment;
import Utils.Utils;
import java.util.ArrayList;

public class ReportController {
    
    PaymentDAO paymentDao = new PaymentDAO();
    double totalPaid;
    ArrayList<Payment> reportList = new ArrayList<>();
    Utils utils = new Utils();
   
    
    public void initReport(String interval){
    
        reportList = paymentDao.searchByTimeInterval(interval);
        totalPaid = utils.caculateTotalPaid(reportList);
        
    }
    
}

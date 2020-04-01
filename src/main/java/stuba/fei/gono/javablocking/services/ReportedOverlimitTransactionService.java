package stuba.fei.gono.javablocking.services;


import stuba.fei.gono.javablocking.pojo.ReportedOverlimitTransaction;


public interface ReportedOverlimitTransactionService {

     ReportedOverlimitTransaction postTransaction(ReportedOverlimitTransaction transaction);
     ReportedOverlimitTransaction getTransactionById(String id);
     ReportedOverlimitTransaction putTransaction(String id, ReportedOverlimitTransaction transaction);
     ReportedOverlimitTransaction deleteTransaction (String id);

}

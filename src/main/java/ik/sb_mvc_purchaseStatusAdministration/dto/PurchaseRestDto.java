package ik.sb_mvc_purchaseStatusAdministration.dto;

import java.util.Date;


public class PurchaseRestDto {

    private String purchaseTitle;
    private int bmig;
    private int estimatedValue;
    private String purchaserName;
    private String status;
    private String purchaseType;
    private Date rfqSent;
    private Date rfqDeadline;
    private Date decisionDate;
    private Date contractSignatureDate;

    public PurchaseRestDto() {
    }

    public String getPurchaseTitle() {
        return purchaseTitle;
    }

    public void setPurchaseTitle(String purchaseTitle) {
        this.purchaseTitle = purchaseTitle;
    }

    public int getBmig() {
        return bmig;
    }

    public void setBmig(int bmig) {
        this.bmig = bmig;
    }

    public int getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(int estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public Date getRfqSent() {
        return rfqSent;
    }

    public void setRfqSent(Date rfqSent) {
        this.rfqSent = rfqSent;
    }

    public Date getRfqDeadline() {
        return rfqDeadline;
    }

    public void setRfqDeadline(Date rfqDeadline) {
        this.rfqDeadline = rfqDeadline;
    }

    public Date getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(Date decisionDate) {
        this.decisionDate = decisionDate;
    }

    public Date getContractSignatureDate() {
        return contractSignatureDate;
    }

    public void setContractSignatureDate(Date contractSignatureDate) {
        this.contractSignatureDate = contractSignatureDate;
    }
}

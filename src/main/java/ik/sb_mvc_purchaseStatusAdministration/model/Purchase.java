package ik.sb_mvc_purchaseStatusAdministration.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "bmig", nullable = false, unique = true)
    private int bmig;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "estimated_value", nullable = false)
    private int estimatedValue;

    @JoinColumn(name = "purchaser_id", nullable = false)
    @ManyToOne(targetEntity = User.class)
    private User purchaser;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "purchase_type", nullable = false)
    private String purchaseType;

    @Column(name = "rfq_sent")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rfqSent;

    @Column(name = "rfq_deadline")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rfqDeadline;

    @Column(name = "decision_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date decisionDate;

    @Column(name = "contract_signature_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date contractSignatureDate;

    @JoinColumn(name = "department_id")
    @ManyToOne(targetEntity = Department.class)
    private Department department;

    public Purchase() {
    }

    public Purchase(int id, int bmig, String title, int estimatedValue, User purchaser, String status,
                    String purchaseType, Date rfqSent, Date rfqDeadline, Date decisionDate, Date contractSignatureDate, Department department) {
        this.id = id;
        this.bmig = bmig;
        this.title = title;
        this.estimatedValue = estimatedValue;
        this.purchaser = purchaser;
        this.status = status;
        this.purchaseType = purchaseType;
        this.rfqSent = rfqSent;
        this.rfqDeadline = rfqDeadline;
        this.decisionDate = decisionDate;
        this.contractSignatureDate = contractSignatureDate;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBmig() {
        return bmig;
    }

    public void setBmig(int bmig) {
        this.bmig = bmig;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(int estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public User getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(User purchaser) {
        this.purchaser = purchaser;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setContractSignatureDate(Date contractSignatureDate) {
        this.contractSignatureDate = contractSignatureDate;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", bmig=" + bmig +
                ", title='" + title + '\'' +
                ", estimatedValue=" + estimatedValue +
                ", purchaser=" + purchaser +
                ", status='" + status + '\'' +
                ", purchaseType='" + purchaseType + '\'' +
                ", rfqSent=" + rfqSent +
                ", rfqDeadline=" + rfqDeadline +
                ", decisionDate=" + decisionDate +
                ", contractSignatureDate=" + contractSignatureDate +
                ", department=" + department +
                '}';
    }
}

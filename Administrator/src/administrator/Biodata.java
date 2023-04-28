/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package administrator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author SOA-NETBOOK
 */
@Entity
@Table(name = "biodata",schema = "")
@NamedQueries({@NamedQuery(name = "Biodata.findByIdBioData", query = "SELECT b FROM Biodata b WHERE b.idBioData = :idBioData"), @NamedQuery(name = "Biodata.findBySurname", query = "SELECT b FROM Biodata b WHERE b.surname = :surname"), @NamedQuery(name = "Biodata.findByOtherNames", query = "SELECT b FROM Biodata b WHERE b.otherNames = :otherNames"), @NamedQuery(name = "Biodata.findByBankAccount", query = "SELECT b FROM Biodata b WHERE b.bankAccount = :bankAccount"), @NamedQuery(name = "Biodata.findByPhoneNo", query = "SELECT b FROM Biodata b WHERE b.phoneNo = :phoneNo"), @NamedQuery(name = "Biodata.findByDateReg", query = "SELECT b FROM Biodata b WHERE b.dateReg = :dateReg")})
public class Biodata implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idBioData", nullable = false)
    private Integer idBioData;
    @Column(name = "surname")
    private String surname;
    @Column(name = "otherNames")
    private String otherNames;
    @Column(name = "bankAccount")
    private String bankAccount;
    @Column(name = "phoneNo")
    private String phoneNo;
    @Column(name = "dateReg")
    private String dateReg;

    public Biodata() {
    }

    public Biodata(Integer idBioData) {
        this.idBioData = idBioData;
    }

    public Integer getIdBioData() {
        return idBioData;
    }

    public void setIdBioData(Integer idBioData) {
        Integer oldIdBioData = this.idBioData;
        this.idBioData = idBioData;
        changeSupport.firePropertyChange("idBioData", oldIdBioData, idBioData);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        String oldSurname = this.surname;
        this.surname = surname;
        changeSupport.firePropertyChange("surname", oldSurname, surname);
    }

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        String oldOtherNames = this.otherNames;
        this.otherNames = otherNames;
        changeSupport.firePropertyChange("otherNames", oldOtherNames, otherNames);
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        String oldBankAccount = this.bankAccount;
        this.bankAccount = bankAccount;
        changeSupport.firePropertyChange("bankAccount", oldBankAccount, bankAccount);
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        String oldPhoneNo = this.phoneNo;
        this.phoneNo = phoneNo;
        changeSupport.firePropertyChange("phoneNo", oldPhoneNo, phoneNo);
    }

    public String getDateReg() {
        return dateReg;
    }

    public void setDateReg(String dateReg) {
        String oldDateReg = this.dateReg;
        this.dateReg = dateReg;
        changeSupport.firePropertyChange("dateReg", oldDateReg, dateReg);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBioData != null ? idBioData.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Biodata)) {
            return false;
        }
        Biodata other = (Biodata) object;
        if ((this.idBioData == null && other.idBioData != null) || (this.idBioData != null && !this.idBioData.equals(other.idBioData))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "administrator.Biodata[idBioData=" + idBioData + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}

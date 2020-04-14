package model;
import java.util.*;

public class Klinika {
   private String naziv;
   private String adresa;
   private String opis;
   private Double prosecnaOcena;
   private int brojOcena;
   
   public java.util.Collection<Pacijent> patient;
   public java.util.Collection<Pregled> pregled;
   public java.util.Collection<Cenovnik> cenovnik;
   public java.util.Collection<AdminKlinike> adminKlinike;
   public java.util.Collection<Operacija> operacija;
   public java.util.Collection<Lekar> lekar;
   public java.util.Collection<MedSestra> medSestra;
   
   
   public java.util.Collection<Pacijent> getPatient() {
      if (patient == null)
         patient = new java.util.HashSet<Pacijent>();
      return patient;
   }
   
   public java.util.Iterator getIteratorPatient() {
      if (patient == null)
         patient = new java.util.HashSet<Pacijent>();
      return patient.iterator();
   }
   
   public void setPatient(java.util.Collection<Pacijent> newPatient) {
      removeAllPatient();
      for (java.util.Iterator iter = newPatient.iterator(); iter.hasNext();)
         addPatient((Pacijent)iter.next());
   }
   
   public void addPatient(Pacijent newPatient) {
      if (newPatient == null)
         return;
      if (this.patient == null)
         this.patient = new java.util.HashSet<Pacijent>();
      if (!this.patient.contains(newPatient))
         this.patient.add(newPatient);
   }
   
   public void removePatient(Pacijent oldPatient) {
      if (oldPatient == null)
         return;
      if (this.patient != null)
         if (this.patient.contains(oldPatient))
            this.patient.remove(oldPatient);
   }
   
   public void removeAllPatient() {
      if (patient != null)
         patient.clear();
   }
   public java.util.Collection<Pregled> getPregled() {
      if (pregled == null)
         pregled = new java.util.HashSet<Pregled>();
      return pregled;
   }
   
   public java.util.Iterator getIteratorPregled() {
      if (pregled == null)
         pregled = new java.util.HashSet<Pregled>();
      return pregled.iterator();
   }
   
   public void setPregled(java.util.Collection<Pregled> newPregled) {
      removeAllPregled();
      for (java.util.Iterator iter = newPregled.iterator(); iter.hasNext();)
         addPregled((Pregled)iter.next());
   }
   
   public void addPregled(Pregled newPregled) {
      if (newPregled == null)
         return;
      if (this.pregled == null)
         this.pregled = new java.util.HashSet<Pregled>();
      if (!this.pregled.contains(newPregled))
         this.pregled.add(newPregled);
   }
   
   public void removePregled(Pregled oldPregled) {
      if (oldPregled == null)
         return;
      if (this.pregled != null)
         if (this.pregled.contains(oldPregled))
            this.pregled.remove(oldPregled);
   }
   
   public void removeAllPregled() {
      if (pregled != null)
         pregled.clear();
   }
   public java.util.Collection<Cenovnik> getCenovnik() {
      if (cenovnik == null)
         cenovnik = new java.util.HashSet<Cenovnik>();
      return cenovnik;
   }
   
   public java.util.Iterator getIteratorCenovnik() {
      if (cenovnik == null)
         cenovnik = new java.util.HashSet<Cenovnik>();
      return cenovnik.iterator();
   }
   
   public void setCenovnik(java.util.Collection<Cenovnik> newCenovnik) {
      removeAllCenovnik();
      for (java.util.Iterator iter = newCenovnik.iterator(); iter.hasNext();)
         addCenovnik((Cenovnik)iter.next());
   }
   
   public void addCenovnik(Cenovnik newCenovnik) {
      if (newCenovnik == null)
         return;
      if (this.cenovnik == null)
         this.cenovnik = new java.util.HashSet<Cenovnik>();
      if (!this.cenovnik.contains(newCenovnik))
         this.cenovnik.add(newCenovnik);
   }
   
   public void removeCenovnik(Cenovnik oldCenovnik) {
      if (oldCenovnik == null)
         return;
      if (this.cenovnik != null)
         if (this.cenovnik.contains(oldCenovnik))
            this.cenovnik.remove(oldCenovnik);
   }
   
   public void removeAllCenovnik() {
      if (cenovnik != null)
         cenovnik.clear();
   }
   public java.util.Collection<AdminKlinike> getAdminKlinike() {
      if (adminKlinike == null)
         adminKlinike = new java.util.HashSet<AdminKlinike>();
      return adminKlinike;
   }
   
   public java.util.Iterator getIteratorAdminKlinike() {
      if (adminKlinike == null)
         adminKlinike = new java.util.HashSet<AdminKlinike>();
      return adminKlinike.iterator();
   }
   
   public void setAdminKlinike(java.util.Collection<AdminKlinike> newAdminKlinike) {
      removeAllAdminKlinike();
      for (java.util.Iterator iter = newAdminKlinike.iterator(); iter.hasNext();)
         addAdminKlinike((AdminKlinike)iter.next());
   }
   
   public void addAdminKlinike(AdminKlinike newAdminKlinike) {
      if (newAdminKlinike == null)
         return;
      if (this.adminKlinike == null)
         this.adminKlinike = new java.util.HashSet<AdminKlinike>();
      if (!this.adminKlinike.contains(newAdminKlinike))
      {
         this.adminKlinike.add(newAdminKlinike);
         newAdminKlinike.setKlinika(this);      
      }
   }
   
   public void removeAdminKlinike(AdminKlinike oldAdminKlinike) {
      if (oldAdminKlinike == null)
         return;
      if (this.adminKlinike != null)
         if (this.adminKlinike.contains(oldAdminKlinike))
         {
            this.adminKlinike.remove(oldAdminKlinike);
            oldAdminKlinike.setKlinika((Klinika)null);
         }
   }
   
   public void removeAllAdminKlinike() {
      if (adminKlinike != null)
      {
         AdminKlinike oldAdminKlinike;
         for (java.util.Iterator iter = getIteratorAdminKlinike(); iter.hasNext();)
         {
            oldAdminKlinike = (AdminKlinike)iter.next();
            iter.remove();
            oldAdminKlinike.setKlinika((Klinika)null);
         }
      }
   }
   public java.util.Collection<Operacija> getOperacija() {
      if (operacija == null)
         operacija = new java.util.HashSet<Operacija>();
      return operacija;
   }
   
   public java.util.Iterator getIteratorOperacija() {
      if (operacija == null)
         operacija = new java.util.HashSet<Operacija>();
      return operacija.iterator();
   }
   
   public void setOperacija(java.util.Collection<Operacija> newOperacija) {
      removeAllOperacija();
      for (java.util.Iterator iter = newOperacija.iterator(); iter.hasNext();)
         addOperacija((Operacija)iter.next());
   }
   
   public void addOperacija(Operacija newOperacija) {
      if (newOperacija == null)
         return;
      if (this.operacija == null)
         this.operacija = new java.util.HashSet<Operacija>();
      if (!this.operacija.contains(newOperacija))
         this.operacija.add(newOperacija);
   }
   
   public void removeOperacija(Operacija oldOperacija) {
      if (oldOperacija == null)
         return;
      if (this.operacija != null)
         if (this.operacija.contains(oldOperacija))
            this.operacija.remove(oldOperacija);
   }
   
   public void removeAllOperacija() {
      if (operacija != null)
         operacija.clear();
   }
   public java.util.Collection<Lekar> getLekar() {
      if (lekar == null)
         lekar = new java.util.HashSet<Lekar>();
      return lekar;
   }
   
   public java.util.Iterator getIteratorLekar() {
      if (lekar == null)
         lekar = new java.util.HashSet<Lekar>();
      return lekar.iterator();
   }
   
   public void setLekar(java.util.Collection<Lekar> newLekar) {
      removeAllLekar();
      for (java.util.Iterator iter = newLekar.iterator(); iter.hasNext();)
         addLekar((Lekar)iter.next());
   }
   
   public void addLekar(Lekar newLekar) {
      if (newLekar == null)
         return;
      if (this.lekar == null)
         this.lekar = new java.util.HashSet<Lekar>();
      if (!this.lekar.contains(newLekar))
         this.lekar.add(newLekar);
   }
   
   public void removeLekar(Lekar oldLekar) {
      if (oldLekar == null)
         return;
      if (this.lekar != null)
         if (this.lekar.contains(oldLekar))
            this.lekar.remove(oldLekar);
   }
   
   public void removeAllLekar() {
      if (lekar != null)
         lekar.clear();
   }
   public java.util.Collection<MedSestra> getMedSestra() {
      if (medSestra == null)
         medSestra = new java.util.HashSet<MedSestra>();
      return medSestra;
   }
   
   public java.util.Iterator getIteratorMedSestra() {
      if (medSestra == null)
         medSestra = new java.util.HashSet<MedSestra>();
      return medSestra.iterator();
   }
   
   public void setMedSestra(java.util.Collection<MedSestra> newMedSestra) {
      removeAllMedSestra();
      for (java.util.Iterator iter = newMedSestra.iterator(); iter.hasNext();)
         addMedSestra((MedSestra)iter.next());
   }
   
   public void addMedSestra(MedSestra newMedSestra) {
      if (newMedSestra == null)
         return;
      if (this.medSestra == null)
         this.medSestra = new java.util.HashSet<MedSestra>();
      if (!this.medSestra.contains(newMedSestra))
         this.medSestra.add(newMedSestra);
   }
   
   public void removeMedSestra(MedSestra oldMedSestra) {
      if (oldMedSestra == null)
         return;
      if (this.medSestra != null)
         if (this.medSestra.contains(oldMedSestra))
            this.medSestra.remove(oldMedSestra);
   }
   
   public void removeAllMedSestra() {
      if (medSestra != null)
         medSestra.clear();
   }

}
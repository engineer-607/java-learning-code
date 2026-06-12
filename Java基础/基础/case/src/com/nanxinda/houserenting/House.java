package com.nanxinda.houserenting;

public class House {
    private String name;
    private String telephone;
    private String place;
    private String monthlyRent ;
    private String state;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!name.equals("-1")){
        this.name = name;
        }
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        if(!telephone.equals("-1")) {
            this.telephone = telephone;
        }
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {

        if(!place.equals("-1")){
        this.place = place;
        }
    }

    public String getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(String monthlyRent) {
        if(!monthlyRent.equals("-1")) {
            this.monthlyRent = monthlyRent;
        }

    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if(!state.equals("-1")) {
            this.state = state;
        }
    }

    @Override
    public String toString() {
        return  name + '\t' + telephone + '\t'
                + place + '\t' + monthlyRent +
                '\t' + state ;
    }
}

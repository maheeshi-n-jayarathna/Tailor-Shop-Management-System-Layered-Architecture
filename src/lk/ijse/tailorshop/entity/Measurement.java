package lk.ijse.tailorshop.entity;

public class Measurement implements SuperEntity{
    private String measurementId;
    private String details;
    private double length;
    private double neck;
    private double bust;
    private double downAShoulder;
    private double underBust;
    private double sleeve;
    private double west;
    private double chest;
    private double crotch;
    private double knee;
    private double calf;
    private double hipAndWestDistance;
    private double inseam;
    private double thigh;
    private double hip;
    private double skirtLength;
    private double frockLength;


    public Measurement() {
    }

    public Measurement(String measurementId, String details, double length, double neck, double bust, double downAShoulder, double underBust, double sleeve, double west, double chest, double crotch, double knee, double calf, double hipAndWestDistance, double inseam, double thigh, double hip, double skirtLength, double frockLength) {
        this.measurementId = measurementId;
        this.details = details;
        this.length = length;
        this.neck = neck;
        this.bust = bust;
        this.downAShoulder = downAShoulder;
        this.underBust = underBust;
        this.sleeve = sleeve;
        this.west = west;
        this.chest = chest;
        this.crotch = crotch;
        this.knee = knee;
        this.calf = calf;
        this.hipAndWestDistance = hipAndWestDistance;
        this.inseam = inseam;
        this.thigh = thigh;
        this.hip = hip;
        this.skirtLength = skirtLength;
        this.frockLength = frockLength;
    }

    public String getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(String measurementId) {
        this.measurementId = measurementId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getNeck() {
        return neck;
    }

    public void setNeck(double neck) {
        this.neck = neck;
    }

    public double getBust() {
        return bust;
    }

    public void setBust(double bust) {
        this.bust = bust;
    }

    public double getDownAShoulder() {
        return downAShoulder;
    }

    public void setDownAShoulder(double downAShoulder) {
        this.downAShoulder = downAShoulder;
    }

    public double getUnderBust() {
        return underBust;
    }

    public void setUnderBust(double underBust) {
        this.underBust = underBust;
    }

    public double getSleeve() {
        return sleeve;
    }

    public void setSleeve(double sleeve) {
        this.sleeve = sleeve;
    }

    public double getWest() {
        return west;
    }

    public void setWest(double west) {
        this.west = west;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public double getCrotch() {
        return crotch;
    }

    public void setCrotch(double crotch) {
        this.crotch = crotch;
    }

    public double getKnee() {
        return knee;
    }

    public void setKnee(double knee) {
        this.knee = knee;
    }

    public double getCalf() {
        return calf;
    }

    public void setCalf(double calf) {
        this.calf = calf;
    }

    public double getHipAndWestDistance() {
        return hipAndWestDistance;
    }

    public void setHipAndWestDistance(double hipAndWestDistance) {
        this.hipAndWestDistance = hipAndWestDistance;
    }

    public double getInseam() {
        return inseam;
    }

    public void setInseam(double inseam) {
        this.inseam = inseam;
    }

    public double getThigh() {
        return thigh;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public double getHip() {
        return hip;
    }

    public void setHip(double hip) {
        this.hip = hip;
    }

    public double getSkirtLength() {
        return skirtLength;
    }

    public void setSkirtLength(double skirtLength) {
        this.skirtLength = skirtLength;
    }

    public double getFrockLength() {
        return frockLength;
    }

    public void setFrockLength(double frockLength) {
        this.frockLength = frockLength;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "measurementId='" + measurementId + '\'' +
                ", details='" + details + '\'' +
                ", length=" + length +
                ", neck=" + neck +
                ", bust=" + bust +
                ", downAShoulder=" + downAShoulder +
                ", underBust=" + underBust +
                ", sleeve=" + sleeve +
                ", west=" + west +
                ", chest=" + chest +
                ", crotch=" + crotch +
                ", knee=" + knee +
                ", calf=" + calf +
                ", hipAndWestDistance=" + hipAndWestDistance +
                ", inseam=" + inseam +
                ", thigh=" + thigh +
                ", hip=" + hip +
                ", skirtLength=" + skirtLength +
                ", frockLength=" + frockLength +
                '}';
    }
}

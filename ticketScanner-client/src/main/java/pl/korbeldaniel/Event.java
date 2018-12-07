package pl.korbeldaniel;

public class Event {
	public int id;
    public String name;
    public String place;
    public String wojewodztwo;
    public String street_number;
    public String city;
    public String createdate;
    public String date;
    public String time;
    public String description;
    public String photo;
    public String photoUrl;
	
    
    @Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", place=" + place + ", wojewodztwo=" + wojewodztwo
				+ ", street_number=" + street_number + ", city=" + city + ", createdate=" + createdate + ", date="
				+ date + ", time=" + time + ", description=" + description + ", photo=" + photo + ", photoUrl="
				+ photoUrl + "]";
	}

    
}


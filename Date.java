
public class Date implements Comparable {
	public String day; //1-31
	public String hour;
	public String month;

	public int compareTo(Object thing) {
		Date thing2 = (Date)thing;
		if (this.month.equals(thing2.month)) {
			return 1;
		} else if (this.day.equals(thing2.day)) {
			return 1;
		} else if (this.hour.equals(thing2.hour)) {
			return 1;
		} return 0; //yay
	}
}


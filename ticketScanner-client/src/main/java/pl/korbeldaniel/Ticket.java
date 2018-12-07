package pl.korbeldaniel;

public class Ticket {

	public int id;
	public String qrCode;
	public String ownerFirstame;
	public boolean checked;
	
	public Ticket() {}
	public Ticket(String qrCode) {
		this.qrCode = qrCode;
	}
	
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", qrCode=" + qrCode + ", ownerFirstame=" + ownerFirstame + ", checked=" + checked
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((qrCode == null) ? 0 : qrCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (qrCode == null) {
			if (other.qrCode != null)
				return false;
		} else if (!qrCode.equals(other.qrCode))
			return false;
		return true;
	}
	
	

}

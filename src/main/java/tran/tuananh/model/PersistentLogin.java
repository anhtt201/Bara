package tran.tuananh.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Persistence")
public class PersistentLogin {

	private String username;

	@Id
	private String series;
	private String token;
	private Timestamp last_used;

	public PersistentLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersistentLogin(String username, String series, String token, Timestamp last_used) {
		super();
		this.username = username;
		this.series = series;
		this.token = token;
		this.last_used = last_used;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getLast_used() {
		return last_used;
	}

	public void setLast_used(Timestamp last_used) {
		this.last_used = last_used;
	}

}

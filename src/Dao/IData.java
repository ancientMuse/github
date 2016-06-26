package Dao;

/**
 * IData entity. @author MyEclipse Persistence Tools
 */

public class IData implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String fromSector;
	private String toSector;
	private String i;
	private String IDataKey;
	private String IDataValue;
	private String time;
	private String datatoken;

	// Constructors

	/** default constructor */
	public IData() {
	}

	/** full constructor */
	public IData(String fromSector, String toSector, String i, String IDataKey,
			String IDataValue, String time, String datatoken) {
		this.fromSector = fromSector;
		this.toSector = toSector;
		this.i = i;
		this.IDataKey = IDataKey;
		this.IDataValue = IDataValue;
		this.time = time;
		this.datatoken = datatoken;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFromSector() {
		return this.fromSector;
	}

	public void setFromSector(String fromSector) {
		this.fromSector = fromSector;
	}

	public String getToSector() {
		return this.toSector;
	}

	public void setToSector(String toSector) {
		this.toSector = toSector;
	}

	public String getI() {
		return this.i;
	}

	public void setI(String i) {
		this.i = i;
	}

	public String getIDataKey() {
		return this.IDataKey;
	}

	public void setIDataKey(String IDataKey) {
		this.IDataKey = IDataKey;
	}

	public String getIDataValue() {
		return this.IDataValue;
	}

	public void setIDataValue(String IDataValue) {
		this.IDataValue = IDataValue;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDatatoken() {
		return this.datatoken;
	}

	public void setDatatoken(String datatoken) {
		this.datatoken = datatoken;
	}

}
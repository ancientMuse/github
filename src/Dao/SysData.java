package Dao;

/**
 * SysData entity. @author MyEclipse Persistence Tools
 */

public class SysData implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String sysKey;
	private String sysValue;
	private String sysSector;

	// Constructors

	/** default constructor */
	public SysData() {
	}

	/** full constructor */
	public SysData(String sytKey, String sysValue, String sysSector) {
		this.sysKey = sytKey;
		this.sysValue = sysValue;
		this.sysSector = sysSector;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSysKey() {
		return this.sysKey;
	}

	public void setSysKey(String sytKey) {
		this.sysKey = sytKey;
	}

	public String getSysValue() {
		return this.sysValue;
	}

	public void setSysValue(String sysValue) {
		this.sysValue = sysValue;
	}

	public String getSysSector() {
		return this.sysSector;
	}

	public void setSysSector(String sysSector) {
		this.sysSector = sysSector;
	}

}
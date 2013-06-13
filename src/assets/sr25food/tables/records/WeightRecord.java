/**
 * This class is generated by jOOQ
 */
package assets.sr25food.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "3.0.1"},
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class WeightRecord extends org.jooq.impl.UpdatableRecordImpl<assets.sr25food.tables.records.WeightRecord> implements org.jooq.Record6<java.lang.String, java.lang.String, java.lang.Double, java.lang.String, java.lang.Double, java.lang.Integer> {

	private static final long serialVersionUID = 1351232244;

	/**
	 * Setter for <code>sr25food.weight.NDB_No</code>. 
	 */
	public void setNdbNo(java.lang.String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>sr25food.weight.NDB_No</code>. 
	 */
	public java.lang.String getNdbNo() {
		return (java.lang.String) getValue(0);
	}

	/**
	 * Setter for <code>sr25food.weight.Seq</code>. 
	 */
	public void setSeq(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>sr25food.weight.Seq</code>. 
	 */
	public java.lang.String getSeq() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>sr25food.weight.Amount</code>. 
	 */
	public void setAmount(java.lang.Double value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>sr25food.weight.Amount</code>. 
	 */
	public java.lang.Double getAmount() {
		return (java.lang.Double) getValue(2);
	}

	/**
	 * Setter for <code>sr25food.weight.Msre_Desc</code>. 
	 */
	public void setMsreDesc(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>sr25food.weight.Msre_Desc</code>. 
	 */
	public java.lang.String getMsreDesc() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>sr25food.weight.Gm_Wgt</code>. 
	 */
	public void setGmWgt(java.lang.Double value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>sr25food.weight.Gm_Wgt</code>. 
	 */
	public java.lang.Double getGmWgt() {
		return (java.lang.Double) getValue(4);
	}

	/**
	 * Setter for <code>sr25food.weight.Num_Data_Pts</code>. 
	 */
	public void setNumDataPts(java.lang.Integer value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>sr25food.weight.Num_Data_Pts</code>. 
	 */
	public java.lang.Integer getNumDataPts() {
		return (java.lang.Integer) getValue(5);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record2<java.lang.String, java.lang.String> key() {
		return (org.jooq.Record2) super.key();
	}

	// -------------------------------------------------------------------------
	// Record6 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row6<java.lang.String, java.lang.String, java.lang.Double, java.lang.String, java.lang.Double, java.lang.Integer> fieldsRow() {
		return (org.jooq.Row6) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row6<java.lang.String, java.lang.String, java.lang.Double, java.lang.String, java.lang.Double, java.lang.Integer> valuesRow() {
		return (org.jooq.Row6) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field1() {
		return assets.sr25food.tables.Weight.WEIGHT.NDB_NO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return assets.sr25food.tables.Weight.WEIGHT.SEQ;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Double> field3() {
		return assets.sr25food.tables.Weight.WEIGHT.AMOUNT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return assets.sr25food.tables.Weight.WEIGHT.MSRE_DESC;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Double> field5() {
		return assets.sr25food.tables.Weight.WEIGHT.GM_WGT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field6() {
		return assets.sr25food.tables.Weight.WEIGHT.NUM_DATA_PTS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value1() {
		return getNdbNo();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getSeq();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Double value3() {
		return getAmount();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getMsreDesc();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Double value5() {
		return getGmWgt();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value6() {
		return getNumDataPts();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached WeightRecord
	 */
	public WeightRecord() {
		super(assets.sr25food.tables.Weight.WEIGHT);
	}
}
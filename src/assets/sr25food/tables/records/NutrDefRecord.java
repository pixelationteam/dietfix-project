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
public class NutrDefRecord extends org.jooq.impl.UpdatableRecordImpl<assets.sr25food.tables.records.NutrDefRecord> implements org.jooq.Record6<java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Double> {

	private static final long serialVersionUID = -1216594232;

	/**
	 * Setter for <code>sr25food.nutr_def.Nutr_No</code>. 
	 */
	public void setNutrNo(java.lang.String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>sr25food.nutr_def.Nutr_No</code>. 
	 */
	public java.lang.String getNutrNo() {
		return (java.lang.String) getValue(0);
	}

	/**
	 * Setter for <code>sr25food.nutr_def.Units</code>. 
	 */
	public void setUnits(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>sr25food.nutr_def.Units</code>. 
	 */
	public java.lang.String getUnits() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>sr25food.nutr_def.Tagname</code>. 
	 */
	public void setTagname(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>sr25food.nutr_def.Tagname</code>. 
	 */
	public java.lang.String getTagname() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>sr25food.nutr_def.NutrDesc</code>. 
	 */
	public void setNutrdesc(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>sr25food.nutr_def.NutrDesc</code>. 
	 */
	public java.lang.String getNutrdesc() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>sr25food.nutr_def.Num_Dec</code>. 
	 */
	public void setNumDec(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>sr25food.nutr_def.Num_Dec</code>. 
	 */
	public java.lang.String getNumDec() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>sr25food.nutr_def.SR_Order</code>. 
	 */
	public void setSrOrder(java.lang.Double value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>sr25food.nutr_def.SR_Order</code>. 
	 */
	public java.lang.Double getSrOrder() {
		return (java.lang.Double) getValue(5);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.String> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record6 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row6<java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Double> fieldsRow() {
		return (org.jooq.Row6) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row6<java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Double> valuesRow() {
		return (org.jooq.Row6) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field1() {
		return assets.sr25food.tables.NutrDef.NUTR_DEF.NUTR_NO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return assets.sr25food.tables.NutrDef.NUTR_DEF.UNITS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return assets.sr25food.tables.NutrDef.NUTR_DEF.TAGNAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return assets.sr25food.tables.NutrDef.NUTR_DEF.NUTRDESC;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return assets.sr25food.tables.NutrDef.NUTR_DEF.NUM_DEC;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Double> field6() {
		return assets.sr25food.tables.NutrDef.NUTR_DEF.SR_ORDER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value1() {
		return getNutrNo();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getUnits();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getTagname();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getNutrdesc();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getNumDec();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Double value6() {
		return getSrOrder();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached NutrDefRecord
	 */
	public NutrDefRecord() {
		super(assets.sr25food.tables.NutrDef.NUTR_DEF);
	}
}

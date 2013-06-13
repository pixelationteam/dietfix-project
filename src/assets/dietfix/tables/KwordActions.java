/**
 * This class is generated by jOOQ
 */
package assets.dietfix.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "3.0.1"},
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class KwordActions extends org.jooq.impl.TableImpl<assets.dietfix.tables.records.KwordActionsRecord> {

	private static final long serialVersionUID = 1487651297;

	/**
	 * The singleton instance of <code>dietfix.kword_actions</code>
	 */
	public static final assets.dietfix.tables.KwordActions KWORD_ACTIONS = new assets.dietfix.tables.KwordActions();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<assets.dietfix.tables.records.KwordActionsRecord> getRecordType() {
		return assets.dietfix.tables.records.KwordActionsRecord.class;
	}

	/**
	 * The column <code>dietfix.kword_actions.ActionID</code>. 
	 */
	public final org.jooq.TableField<assets.dietfix.tables.records.KwordActionsRecord, java.lang.Integer> ACTIONID = createField("ActionID", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>dietfix.kword_actions.KWordID</code>. 
	 */
	public final org.jooq.TableField<assets.dietfix.tables.records.KwordActionsRecord, java.lang.String> KWORDID = createField("KWordID", org.jooq.impl.SQLDataType.VARCHAR.length(255), this);

	/**
	 * Create a <code>dietfix.kword_actions</code> table reference
	 */
	public KwordActions() {
		super("kword_actions", assets.dietfix.Dietfix.DIETFIX);
	}

	/**
	 * Create an aliased <code>dietfix.kword_actions</code> table reference
	 */
	public KwordActions(java.lang.String alias) {
		super(alias, assets.dietfix.Dietfix.DIETFIX, assets.dietfix.tables.KwordActions.KWORD_ACTIONS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<assets.dietfix.tables.records.KwordActionsRecord, java.lang.Integer> getIdentity() {
		return assets.dietfix.Keys.IDENTITY_KWORD_ACTIONS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<assets.dietfix.tables.records.KwordActionsRecord> getPrimaryKey() {
		return assets.dietfix.Keys.KEY_KWORD_ACTIONS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<assets.dietfix.tables.records.KwordActionsRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<assets.dietfix.tables.records.KwordActionsRecord>>asList(assets.dietfix.Keys.KEY_KWORD_ACTIONS_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public assets.dietfix.tables.KwordActions as(java.lang.String alias) {
		return new assets.dietfix.tables.KwordActions(alias);
	}
}

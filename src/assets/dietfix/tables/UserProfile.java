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
public class UserProfile extends org.jooq.impl.TableImpl<assets.dietfix.tables.records.UserProfileRecord> {

	private static final long serialVersionUID = 231802609;

	/**
	 * The singleton instance of <code>dietfix.user_profile</code>
	 */
	public static final assets.dietfix.tables.UserProfile USER_PROFILE = new assets.dietfix.tables.UserProfile();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<assets.dietfix.tables.records.UserProfileRecord> getRecordType() {
		return assets.dietfix.tables.records.UserProfileRecord.class;
	}

	/**
	 * The column <code>dietfix.user_profile.profileID</code>. 
	 */
	public final org.jooq.TableField<assets.dietfix.tables.records.UserProfileRecord, java.lang.Integer> PROFILEID = createField("profileID", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>dietfix.user_profile.userID</code>. 
	 */
	public final org.jooq.TableField<assets.dietfix.tables.records.UserProfileRecord, java.lang.Integer> USERID = createField("userID", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>dietfix.user_profile.historyBackgroundID</code>. 
	 */
	public final org.jooq.TableField<assets.dietfix.tables.records.UserProfileRecord, java.lang.Integer> HISTORYBACKGROUNDID = createField("historyBackgroundID", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * Create a <code>dietfix.user_profile</code> table reference
	 */
	public UserProfile() {
		super("user_profile", assets.dietfix.Dietfix.DIETFIX);
	}

	/**
	 * Create an aliased <code>dietfix.user_profile</code> table reference
	 */
	public UserProfile(java.lang.String alias) {
		super(alias, assets.dietfix.Dietfix.DIETFIX, assets.dietfix.tables.UserProfile.USER_PROFILE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<assets.dietfix.tables.records.UserProfileRecord, java.lang.Integer> getIdentity() {
		return assets.dietfix.Keys.IDENTITY_USER_PROFILE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<assets.dietfix.tables.records.UserProfileRecord> getPrimaryKey() {
		return assets.dietfix.Keys.KEY_USER_PROFILE_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<assets.dietfix.tables.records.UserProfileRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<assets.dietfix.tables.records.UserProfileRecord>>asList(assets.dietfix.Keys.KEY_USER_PROFILE_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<assets.dietfix.tables.records.UserProfileRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<assets.dietfix.tables.records.UserProfileRecord, ?>>asList(assets.dietfix.Keys.USER_PROFILE_IBFK_1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public assets.dietfix.tables.UserProfile as(java.lang.String alias) {
		return new assets.dietfix.tables.UserProfile(alias);
	}
}
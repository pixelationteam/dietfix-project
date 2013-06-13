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
public class NoiseWords extends org.jooq.impl.TableImpl<assets.dietfix.tables.records.NoiseWordsRecord> {

	private static final long serialVersionUID = 1590901980;

	/**
	 * The singleton instance of <code>dietfix.noise_words</code>
	 */
	public static final assets.dietfix.tables.NoiseWords NOISE_WORDS = new assets.dietfix.tables.NoiseWords();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<assets.dietfix.tables.records.NoiseWordsRecord> getRecordType() {
		return assets.dietfix.tables.records.NoiseWordsRecord.class;
	}

	/**
	 * The column <code>dietfix.noise_words.id</code>. 
	 */
	public final org.jooq.TableField<assets.dietfix.tables.records.NoiseWordsRecord, java.lang.Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>dietfix.noise_words.word</code>. 
	 */
	public final org.jooq.TableField<assets.dietfix.tables.records.NoiseWordsRecord, java.lang.String> WORD = createField("word", org.jooq.impl.SQLDataType.VARCHAR.length(50), this);

	/**
	 * Create a <code>dietfix.noise_words</code> table reference
	 */
	public NoiseWords() {
		super("noise_words", assets.dietfix.Dietfix.DIETFIX);
	}

	/**
	 * Create an aliased <code>dietfix.noise_words</code> table reference
	 */
	public NoiseWords(java.lang.String alias) {
		super(alias, assets.dietfix.Dietfix.DIETFIX, assets.dietfix.tables.NoiseWords.NOISE_WORDS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<assets.dietfix.tables.records.NoiseWordsRecord, java.lang.Integer> getIdentity() {
		return assets.dietfix.Keys.IDENTITY_NOISE_WORDS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<assets.dietfix.tables.records.NoiseWordsRecord> getPrimaryKey() {
		return assets.dietfix.Keys.KEY_NOISE_WORDS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<assets.dietfix.tables.records.NoiseWordsRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<assets.dietfix.tables.records.NoiseWordsRecord>>asList(assets.dietfix.Keys.KEY_NOISE_WORDS_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public assets.dietfix.tables.NoiseWords as(java.lang.String alias) {
		return new assets.dietfix.tables.NoiseWords(alias);
	}
}
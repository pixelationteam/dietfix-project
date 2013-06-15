/**
 * This class is generated by jOOQ
 */
package assets.fitness;

/**
 * This class is generated by jOOQ.
 *
 * A class modelling foreign key relationships between tables of the <code>fitness</code> 
 * schema
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "3.0.1"},
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

	// -------------------------------------------------------------------------
	// IDENTITY definitions
	// -------------------------------------------------------------------------

	public static final org.jooq.Identity<assets.fitness.tables.records.ExercisesRecord, java.lang.Integer> IDENTITY_EXERCISES = Identities0.IDENTITY_EXERCISES;

	// -------------------------------------------------------------------------
	// UNIQUE and PRIMARY KEY definitions
	// -------------------------------------------------------------------------

	public static final org.jooq.UniqueKey<assets.fitness.tables.records.ExercisesRecord> KEY_EXERCISES_PRIMARY = UniqueKeys0.KEY_EXERCISES_PRIMARY;

	// -------------------------------------------------------------------------
	// FOREIGN KEY definitions
	// -------------------------------------------------------------------------


	// -------------------------------------------------------------------------
	// [#1459] distribute members to avoid static initialisers > 64kb
	// -------------------------------------------------------------------------

	private static class Identities0 extends org.jooq.impl.AbstractKeys {
		public static org.jooq.Identity<assets.fitness.tables.records.ExercisesRecord, java.lang.Integer> IDENTITY_EXERCISES = createIdentity(assets.fitness.tables.Exercises.EXERCISES, assets.fitness.tables.Exercises.EXERCISES.EXERCISE_ID);
	}

	private static class UniqueKeys0 extends org.jooq.impl.AbstractKeys {
		public static final org.jooq.UniqueKey<assets.fitness.tables.records.ExercisesRecord> KEY_EXERCISES_PRIMARY = createUniqueKey(assets.fitness.tables.Exercises.EXERCISES, assets.fitness.tables.Exercises.EXERCISES.EXERCISE_ID);
	}
}

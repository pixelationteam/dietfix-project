package pup.thesis.expert;

import org.jooq.Field;

import assets.sr25food.Tables;


public enum Nutrients{
WATER(Tables.ABBREV.WATER__G_,"g"),
ENERGY(Tables.ABBREV.ENERG_KCAL,"kcal"),
PROTEIN(Tables.ABBREV.PROTEIN__G_,"g"),
LIPID(Tables.ABBREV.LIPID_TOT__G_,"g"),
ASH(Tables.ABBREV.ASH__G_,"g");


private final String unit; // every unit is based on per 100g of the item;
private final Field<?> field;
private Nutrients(Field<?> field,String unit){
	this.field = field;
	this.unit = unit;
}

public Field<?> getField(){
	return field;
}
public String getUnit(){
	return unit;
}

public static Nutrients valueOf(Field<?> field){
	for(Nutrients f: Nutrients.values()){
		if(f.getField().equals(field)){
			return f;
		}
	}
	return null;
}

}

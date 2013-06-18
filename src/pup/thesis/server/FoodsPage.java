/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pup.thesis.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pup.thesis.helper.MysqlHelper;
import pup.thesis.knowledgebase.expert.Dietitian;
import pup.thesis.knowledgebase.expert.Factor;
import pup.thesis.knowledgebase.expert.FoodCategory;
import pup.thesis.logging.App;
import pup.thesis.logging.Modules;
import pup.thesis.nlu.CoreParser;
import pup.thesis.util.ServerPage;
import pup.thesis.util.mysql.SafeConnection;

/**
 *
 * @author Dell
 */
public class FoodsPage extends ServerPage{
    
        private Dietitian dietitian;
    public FoodsPage(HttpServletRequest request, HttpServletResponse response) {
        super(request,response);
    }


	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
        try {
		dietitian = new Dietitian(this.getClientData());
        } catch (ClassNotFoundException ex) {
            addError("Class not found on parser:"+ex.getMessage());
            App.log(Modules.SYSTEM,"FooosPage:49", ex.getMessage());
        } catch (InstantiationException ex) {
            addError("Instantiation exception:"+ex.getMessage());
            App.log(Modules.SYSTEM,"FooosPage:51", ex.getMessage());
        } catch (IllegalAccessException ex) {
            addError("IllegalAccessException:"+ex.getMessage());
            App.log(Modules.SYSTEM,"FooosPage:54", ex.getMessage());
        } catch (SQLException ex) {
            addError("SQL Exception:"+ex.getMessage());
            App.log(Modules.SYSTEM,"FooosPage:58", ex.getMessage());
        }
	}
    
    
    
    public String test(){
        /*String q = "SELECT fdgrp_desc,Shrt_Desc, concat( Nutr_val, Units ) AS Value " +
"FROM fd_group " +
"JOIN food_des " +
"USING ( fdgrp_cd ) " +
"JOIN nutr_def ON ( NutrDesc = 'Protein' ) " +
"JOIN nut_data " +
"USING ( Nutr_No, ndb_no ) " +
"WHERE fdgrp_desc like '%LEGUME%' " +
"ORDER BY Nutr_Val DESC  " +
"LIMIT 100";*/
    	
         StringBuilder ret = new StringBuilder();
         
         try {
        	 List<FoodCategory> cat = dietitian.getFoodCategories();
			for(FoodCategory ff:cat){
				ret.append(String.format("<option val='%s'>%s</option>", ff.id,ff.description));
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			App.log(Modules.SYSTEM,"Error:"+e.getMessage());
		}
       
         
         return ret.toString();
    }


}

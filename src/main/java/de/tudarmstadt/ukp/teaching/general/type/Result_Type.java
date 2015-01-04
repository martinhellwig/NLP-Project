
/* First created by JCasGen Sat Jan 03 12:08:05 CET 2015 */
package de.tudarmstadt.ukp.teaching.general.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Sat Jan 03 12:08:05 CET 2015
 * @generated */
public class Result_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Result_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Result_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Result(addr, Result_Type.this);
  			   Result_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Result(addr, Result_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Result.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.tudarmstadt.ukp.teaching.general.type.Result");
 
  /** @generated */
  final Feature casFeat_ressouceType;
  /** @generated */
  final int     casFeatCode_ressouceType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getRessouceType(int addr) {
        if (featOkTst && casFeat_ressouceType == null)
      jcas.throwFeatMissing("ressouceType", "de.tudarmstadt.ukp.teaching.general.type.Result");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ressouceType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setRessouceType(int addr, String v) {
        if (featOkTst && casFeat_ressouceType == null)
      jcas.throwFeatMissing("ressouceType", "de.tudarmstadt.ukp.teaching.general.type.Result");
    ll_cas.ll_setStringValue(addr, casFeatCode_ressouceType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_answer1Possibility;
  /** @generated */
  final int     casFeatCode_answer1Possibility;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public float getAnswer1Possibility(int addr) {
        if (featOkTst && casFeat_answer1Possibility == null)
      jcas.throwFeatMissing("answer1Possibility", "de.tudarmstadt.ukp.teaching.general.type.Result");
    return ll_cas.ll_getFloatValue(addr, casFeatCode_answer1Possibility);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnswer1Possibility(int addr, float v) {
        if (featOkTst && casFeat_answer1Possibility == null)
      jcas.throwFeatMissing("answer1Possibility", "de.tudarmstadt.ukp.teaching.general.type.Result");
    ll_cas.ll_setFloatValue(addr, casFeatCode_answer1Possibility, v);}
    
  
 
  /** @generated */
  final Feature casFeat_answer2Possibility;
  /** @generated */
  final int     casFeatCode_answer2Possibility;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public float getAnswer2Possibility(int addr) {
        if (featOkTst && casFeat_answer2Possibility == null)
      jcas.throwFeatMissing("answer2Possibility", "de.tudarmstadt.ukp.teaching.general.type.Result");
    return ll_cas.ll_getFloatValue(addr, casFeatCode_answer2Possibility);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnswer2Possibility(int addr, float v) {
        if (featOkTst && casFeat_answer2Possibility == null)
      jcas.throwFeatMissing("answer2Possibility", "de.tudarmstadt.ukp.teaching.general.type.Result");
    ll_cas.ll_setFloatValue(addr, casFeatCode_answer2Possibility, v);}
    
  
 
  /** @generated */
  final Feature casFeat_answer3Possibility;
  /** @generated */
  final int     casFeatCode_answer3Possibility;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public float getAnswer3Possibility(int addr) {
        if (featOkTst && casFeat_answer3Possibility == null)
      jcas.throwFeatMissing("answer3Possibility", "de.tudarmstadt.ukp.teaching.general.type.Result");
    return ll_cas.ll_getFloatValue(addr, casFeatCode_answer3Possibility);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnswer3Possibility(int addr, float v) {
        if (featOkTst && casFeat_answer3Possibility == null)
      jcas.throwFeatMissing("answer3Possibility", "de.tudarmstadt.ukp.teaching.general.type.Result");
    ll_cas.ll_setFloatValue(addr, casFeatCode_answer3Possibility, v);}
    
  
 
  /** @generated */
  final Feature casFeat_answer4Possibility;
  /** @generated */
  final int     casFeatCode_answer4Possibility;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public float getAnswer4Possibility(int addr) {
        if (featOkTst && casFeat_answer4Possibility == null)
      jcas.throwFeatMissing("answer4Possibility", "de.tudarmstadt.ukp.teaching.general.type.Result");
    return ll_cas.ll_getFloatValue(addr, casFeatCode_answer4Possibility);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnswer4Possibility(int addr, float v) {
        if (featOkTst && casFeat_answer4Possibility == null)
      jcas.throwFeatMissing("answer4Possibility", "de.tudarmstadt.ukp.teaching.general.type.Result");
    ll_cas.ll_setFloatValue(addr, casFeatCode_answer4Possibility, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Result_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_ressouceType = jcas.getRequiredFeatureDE(casType, "ressouceType", "uima.cas.String", featOkTst);
    casFeatCode_ressouceType  = (null == casFeat_ressouceType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ressouceType).getCode();

 
    casFeat_answer1Possibility = jcas.getRequiredFeatureDE(casType, "answer1Possibility", "uima.cas.Float", featOkTst);
    casFeatCode_answer1Possibility  = (null == casFeat_answer1Possibility) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_answer1Possibility).getCode();

 
    casFeat_answer2Possibility = jcas.getRequiredFeatureDE(casType, "answer2Possibility", "uima.cas.Float", featOkTst);
    casFeatCode_answer2Possibility  = (null == casFeat_answer2Possibility) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_answer2Possibility).getCode();

 
    casFeat_answer3Possibility = jcas.getRequiredFeatureDE(casType, "answer3Possibility", "uima.cas.Float", featOkTst);
    casFeatCode_answer3Possibility  = (null == casFeat_answer3Possibility) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_answer3Possibility).getCode();

 
    casFeat_answer4Possibility = jcas.getRequiredFeatureDE(casType, "answer4Possibility", "uima.cas.Float", featOkTst);
    casFeatCode_answer4Possibility  = (null == casFeat_answer4Possibility) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_answer4Possibility).getCode();

  }
}



    
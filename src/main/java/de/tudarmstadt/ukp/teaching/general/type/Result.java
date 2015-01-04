

/* First created by JCasGen Sat Jan 03 12:08:05 CET 2015 */
package de.tudarmstadt.ukp.teaching.general.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sat Jan 03 12:08:05 CET 2015
 * XML source: C:/Users/Martin/workspace/de.tudarmstadt.lt.teaching.nlp4web.project/src/main/resources/desc/type/Result.xml
 * @generated */
public class Result extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Result.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Result() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Result(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Result(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Result(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: ressouceType

  /** getter for ressouceType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getRessouceType() {
    if (Result_Type.featOkTst && ((Result_Type)jcasType).casFeat_ressouceType == null)
      jcasType.jcas.throwFeatMissing("ressouceType", "de.tudarmstadt.ukp.teaching.general.type.Result");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Result_Type)jcasType).casFeatCode_ressouceType);}
    
  /** setter for ressouceType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setRessouceType(String v) {
    if (Result_Type.featOkTst && ((Result_Type)jcasType).casFeat_ressouceType == null)
      jcasType.jcas.throwFeatMissing("ressouceType", "de.tudarmstadt.ukp.teaching.general.type.Result");
    jcasType.ll_cas.ll_setStringValue(addr, ((Result_Type)jcasType).casFeatCode_ressouceType, v);}    
   
    
  //*--------------*
  //* Feature: answer1Possibility

  /** getter for answer1Possibility - gets 
   * @generated
   * @return value of the feature 
   */
  public float getAnswer1Possibility() {
    if (Result_Type.featOkTst && ((Result_Type)jcasType).casFeat_answer1Possibility == null)
      jcasType.jcas.throwFeatMissing("answer1Possibility", "de.tudarmstadt.ukp.teaching.general.type.Result");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((Result_Type)jcasType).casFeatCode_answer1Possibility);}
    
  /** setter for answer1Possibility - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnswer1Possibility(float v) {
    if (Result_Type.featOkTst && ((Result_Type)jcasType).casFeat_answer1Possibility == null)
      jcasType.jcas.throwFeatMissing("answer1Possibility", "de.tudarmstadt.ukp.teaching.general.type.Result");
    jcasType.ll_cas.ll_setFloatValue(addr, ((Result_Type)jcasType).casFeatCode_answer1Possibility, v);}    
   
    
  //*--------------*
  //* Feature: answer2Possibility

  /** getter for answer2Possibility - gets 
   * @generated
   * @return value of the feature 
   */
  public float getAnswer2Possibility() {
    if (Result_Type.featOkTst && ((Result_Type)jcasType).casFeat_answer2Possibility == null)
      jcasType.jcas.throwFeatMissing("answer2Possibility", "de.tudarmstadt.ukp.teaching.general.type.Result");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((Result_Type)jcasType).casFeatCode_answer2Possibility);}
    
  /** setter for answer2Possibility - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnswer2Possibility(float v) {
    if (Result_Type.featOkTst && ((Result_Type)jcasType).casFeat_answer2Possibility == null)
      jcasType.jcas.throwFeatMissing("answer2Possibility", "de.tudarmstadt.ukp.teaching.general.type.Result");
    jcasType.ll_cas.ll_setFloatValue(addr, ((Result_Type)jcasType).casFeatCode_answer2Possibility, v);}    
   
    
  //*--------------*
  //* Feature: answer3Possibility

  /** getter for answer3Possibility - gets 
   * @generated
   * @return value of the feature 
   */
  public float getAnswer3Possibility() {
    if (Result_Type.featOkTst && ((Result_Type)jcasType).casFeat_answer3Possibility == null)
      jcasType.jcas.throwFeatMissing("answer3Possibility", "de.tudarmstadt.ukp.teaching.general.type.Result");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((Result_Type)jcasType).casFeatCode_answer3Possibility);}
    
  /** setter for answer3Possibility - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnswer3Possibility(float v) {
    if (Result_Type.featOkTst && ((Result_Type)jcasType).casFeat_answer3Possibility == null)
      jcasType.jcas.throwFeatMissing("answer3Possibility", "de.tudarmstadt.ukp.teaching.general.type.Result");
    jcasType.ll_cas.ll_setFloatValue(addr, ((Result_Type)jcasType).casFeatCode_answer3Possibility, v);}    
   
    
  //*--------------*
  //* Feature: answer4Possibility

  /** getter for answer4Possibility - gets 
   * @generated
   * @return value of the feature 
   */
  public float getAnswer4Possibility() {
    if (Result_Type.featOkTst && ((Result_Type)jcasType).casFeat_answer4Possibility == null)
      jcasType.jcas.throwFeatMissing("answer4Possibility", "de.tudarmstadt.ukp.teaching.general.type.Result");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((Result_Type)jcasType).casFeatCode_answer4Possibility);}
    
  /** setter for answer4Possibility - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnswer4Possibility(float v) {
    if (Result_Type.featOkTst && ((Result_Type)jcasType).casFeat_answer4Possibility == null)
      jcasType.jcas.throwFeatMissing("answer4Possibility", "de.tudarmstadt.ukp.teaching.general.type.Result");
    jcasType.ll_cas.ll_setFloatValue(addr, ((Result_Type)jcasType).casFeatCode_answer4Possibility, v);}    
  }

    
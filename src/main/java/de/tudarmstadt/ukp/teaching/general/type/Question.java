

/* First created by JCasGen Fri Jan 02 22:56:01 CET 2015 */
package de.tudarmstadt.ukp.teaching.general.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sat Jan 03 12:07:51 CET 2015
 * XML source: C:/Users/Martin/workspace/de.tudarmstadt.lt.teaching.nlp4web.project/src/main/resources/desc/type/Question.xml
 * @generated */
public class Question extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Question.class);
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
  protected Question() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Question(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Question(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Question(JCas jcas, int begin, int end) {
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
  //* Feature: question

  /** getter for question - gets 
   * @generated
   * @return value of the feature 
   */
  public String getQuestion() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_question == null)
      jcasType.jcas.throwFeatMissing("question", "de.tudarmstadt.ukp.teaching.general.type.Question");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Question_Type)jcasType).casFeatCode_question);}
    
  /** setter for question - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuestion(String v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_question == null)
      jcasType.jcas.throwFeatMissing("question", "de.tudarmstadt.ukp.teaching.general.type.Question");
    jcasType.ll_cas.ll_setStringValue(addr, ((Question_Type)jcasType).casFeatCode_question, v);}    
   
    
  //*--------------*
  //* Feature: answer1

  /** getter for answer1 - gets 
   * @generated
   * @return value of the feature 
   */
  public String getAnswer1() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_answer1 == null)
      jcasType.jcas.throwFeatMissing("answer1", "de.tudarmstadt.ukp.teaching.general.type.Question");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Question_Type)jcasType).casFeatCode_answer1);}
    
  /** setter for answer1 - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnswer1(String v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_answer1 == null)
      jcasType.jcas.throwFeatMissing("answer1", "de.tudarmstadt.ukp.teaching.general.type.Question");
    jcasType.ll_cas.ll_setStringValue(addr, ((Question_Type)jcasType).casFeatCode_answer1, v);}    
   
    
  //*--------------*
  //* Feature: answer2

  /** getter for answer2 - gets 
   * @generated
   * @return value of the feature 
   */
  public String getAnswer2() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_answer2 == null)
      jcasType.jcas.throwFeatMissing("answer2", "de.tudarmstadt.ukp.teaching.general.type.Question");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Question_Type)jcasType).casFeatCode_answer2);}
    
  /** setter for answer2 - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnswer2(String v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_answer2 == null)
      jcasType.jcas.throwFeatMissing("answer2", "de.tudarmstadt.ukp.teaching.general.type.Question");
    jcasType.ll_cas.ll_setStringValue(addr, ((Question_Type)jcasType).casFeatCode_answer2, v);}    
   
    
  //*--------------*
  //* Feature: answer3

  /** getter for answer3 - gets 
   * @generated
   * @return value of the feature 
   */
  public String getAnswer3() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_answer3 == null)
      jcasType.jcas.throwFeatMissing("answer3", "de.tudarmstadt.ukp.teaching.general.type.Question");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Question_Type)jcasType).casFeatCode_answer3);}
    
  /** setter for answer3 - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnswer3(String v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_answer3 == null)
      jcasType.jcas.throwFeatMissing("answer3", "de.tudarmstadt.ukp.teaching.general.type.Question");
    jcasType.ll_cas.ll_setStringValue(addr, ((Question_Type)jcasType).casFeatCode_answer3, v);}    
   
    
  //*--------------*
  //* Feature: answer4

  /** getter for answer4 - gets 
   * @generated
   * @return value of the feature 
   */
  public String getAnswer4() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_answer4 == null)
      jcasType.jcas.throwFeatMissing("answer4", "de.tudarmstadt.ukp.teaching.general.type.Question");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Question_Type)jcasType).casFeatCode_answer4);}
    
  /** setter for answer4 - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnswer4(String v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_answer4 == null)
      jcasType.jcas.throwFeatMissing("answer4", "de.tudarmstadt.ukp.teaching.general.type.Question");
    jcasType.ll_cas.ll_setStringValue(addr, ((Question_Type)jcasType).casFeatCode_answer4, v);}    
   
    
  //*--------------*
  //* Feature: rightAnswer

  /** getter for rightAnswer - gets 
   * @generated
   * @return value of the feature 
   */
  public String getRightAnswer() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_rightAnswer == null)
      jcasType.jcas.throwFeatMissing("rightAnswer", "de.tudarmstadt.ukp.teaching.general.type.Question");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Question_Type)jcasType).casFeatCode_rightAnswer);}
    
  /** setter for rightAnswer - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setRightAnswer(String v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_rightAnswer == null)
      jcasType.jcas.throwFeatMissing("rightAnswer", "de.tudarmstadt.ukp.teaching.general.type.Question");
    jcasType.ll_cas.ll_setStringValue(addr, ((Question_Type)jcasType).casFeatCode_rightAnswer, v);}    
  }

    
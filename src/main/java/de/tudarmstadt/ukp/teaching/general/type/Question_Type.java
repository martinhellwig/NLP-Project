
/* First created by JCasGen Fri Jan 02 22:56:01 CET 2015 */
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
 * Updated by JCasGen Sat Jan 03 12:07:51 CET 2015
 * @generated */
public class Question_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Question_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Question_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Question(addr, Question_Type.this);
  			   Question_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Question(addr, Question_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Question.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.tudarmstadt.ukp.teaching.general.type.Question");
 
  /** @generated */
  final Feature casFeat_question;
  /** @generated */
  final int     casFeatCode_question;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getQuestion(int addr) {
        if (featOkTst && casFeat_question == null)
      jcas.throwFeatMissing("question", "de.tudarmstadt.ukp.teaching.general.type.Question");
    return ll_cas.ll_getStringValue(addr, casFeatCode_question);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuestion(int addr, String v) {
        if (featOkTst && casFeat_question == null)
      jcas.throwFeatMissing("question", "de.tudarmstadt.ukp.teaching.general.type.Question");
    ll_cas.ll_setStringValue(addr, casFeatCode_question, v);}
    
  
 
  /** @generated */
  final Feature casFeat_answer1;
  /** @generated */
  final int     casFeatCode_answer1;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getAnswer1(int addr) {
        if (featOkTst && casFeat_answer1 == null)
      jcas.throwFeatMissing("answer1", "de.tudarmstadt.ukp.teaching.general.type.Question");
    return ll_cas.ll_getStringValue(addr, casFeatCode_answer1);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnswer1(int addr, String v) {
        if (featOkTst && casFeat_answer1 == null)
      jcas.throwFeatMissing("answer1", "de.tudarmstadt.ukp.teaching.general.type.Question");
    ll_cas.ll_setStringValue(addr, casFeatCode_answer1, v);}
    
  
 
  /** @generated */
  final Feature casFeat_answer2;
  /** @generated */
  final int     casFeatCode_answer2;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getAnswer2(int addr) {
        if (featOkTst && casFeat_answer2 == null)
      jcas.throwFeatMissing("answer2", "de.tudarmstadt.ukp.teaching.general.type.Question");
    return ll_cas.ll_getStringValue(addr, casFeatCode_answer2);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnswer2(int addr, String v) {
        if (featOkTst && casFeat_answer2 == null)
      jcas.throwFeatMissing("answer2", "de.tudarmstadt.ukp.teaching.general.type.Question");
    ll_cas.ll_setStringValue(addr, casFeatCode_answer2, v);}
    
  
 
  /** @generated */
  final Feature casFeat_answer3;
  /** @generated */
  final int     casFeatCode_answer3;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getAnswer3(int addr) {
        if (featOkTst && casFeat_answer3 == null)
      jcas.throwFeatMissing("answer3", "de.tudarmstadt.ukp.teaching.general.type.Question");
    return ll_cas.ll_getStringValue(addr, casFeatCode_answer3);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnswer3(int addr, String v) {
        if (featOkTst && casFeat_answer3 == null)
      jcas.throwFeatMissing("answer3", "de.tudarmstadt.ukp.teaching.general.type.Question");
    ll_cas.ll_setStringValue(addr, casFeatCode_answer3, v);}
    
  
 
  /** @generated */
  final Feature casFeat_answer4;
  /** @generated */
  final int     casFeatCode_answer4;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getAnswer4(int addr) {
        if (featOkTst && casFeat_answer4 == null)
      jcas.throwFeatMissing("answer4", "de.tudarmstadt.ukp.teaching.general.type.Question");
    return ll_cas.ll_getStringValue(addr, casFeatCode_answer4);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnswer4(int addr, String v) {
        if (featOkTst && casFeat_answer4 == null)
      jcas.throwFeatMissing("answer4", "de.tudarmstadt.ukp.teaching.general.type.Question");
    ll_cas.ll_setStringValue(addr, casFeatCode_answer4, v);}
    
  
 
  /** @generated */
  final Feature casFeat_rightAnswer;
  /** @generated */
  final int     casFeatCode_rightAnswer;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getRightAnswer(int addr) {
        if (featOkTst && casFeat_rightAnswer == null)
      jcas.throwFeatMissing("rightAnswer", "de.tudarmstadt.ukp.teaching.general.type.Question");
    return ll_cas.ll_getStringValue(addr, casFeatCode_rightAnswer);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setRightAnswer(int addr, String v) {
        if (featOkTst && casFeat_rightAnswer == null)
      jcas.throwFeatMissing("rightAnswer", "de.tudarmstadt.ukp.teaching.general.type.Question");
    ll_cas.ll_setStringValue(addr, casFeatCode_rightAnswer, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Question_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_question = jcas.getRequiredFeatureDE(casType, "question", "uima.cas.String", featOkTst);
    casFeatCode_question  = (null == casFeat_question) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_question).getCode();

 
    casFeat_answer1 = jcas.getRequiredFeatureDE(casType, "answer1", "uima.cas.String", featOkTst);
    casFeatCode_answer1  = (null == casFeat_answer1) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_answer1).getCode();

 
    casFeat_answer2 = jcas.getRequiredFeatureDE(casType, "answer2", "uima.cas.String", featOkTst);
    casFeatCode_answer2  = (null == casFeat_answer2) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_answer2).getCode();

 
    casFeat_answer3 = jcas.getRequiredFeatureDE(casType, "answer3", "uima.cas.String", featOkTst);
    casFeatCode_answer3  = (null == casFeat_answer3) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_answer3).getCode();

 
    casFeat_answer4 = jcas.getRequiredFeatureDE(casType, "answer4", "uima.cas.String", featOkTst);
    casFeatCode_answer4  = (null == casFeat_answer4) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_answer4).getCode();

 
    casFeat_rightAnswer = jcas.getRequiredFeatureDE(casType, "rightAnswer", "uima.cas.String", featOkTst);
    casFeatCode_rightAnswer  = (null == casFeat_rightAnswer) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_rightAnswer).getCode();

  }
}



    
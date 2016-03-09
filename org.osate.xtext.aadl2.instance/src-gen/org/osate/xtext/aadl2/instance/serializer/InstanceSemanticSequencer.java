/*
 * generated by Xtext
 */
package org.osate.xtext.aadl2.instance.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.ConnectionReference;
import org.osate.aadl2.instance.EndToEndFlowInstance;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.instance.FlowSpecificationInstance;
import org.osate.aadl2.instance.InstancePackage;
import org.osate.aadl2.instance.ModeInstance;
import org.osate.aadl2.instance.ModeTransitionInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.xtext.aadl2.instance.services.InstanceGrammarAccess;

@SuppressWarnings("all")
public class InstanceSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private InstanceGrammarAccess grammarAccess;
	
	@Override
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == InstancePackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case InstancePackage.COMPONENT_INSTANCE:
				sequence_ComponentInstance(context, (ComponentInstance) semanticObject); 
				return; 
			case InstancePackage.CONNECTION_INSTANCE:
				sequence_ConnectionInstance(context, (ConnectionInstance) semanticObject); 
				return; 
			case InstancePackage.CONNECTION_REFERENCE:
				sequence_ConnectionReference(context, (ConnectionReference) semanticObject); 
				return; 
			case InstancePackage.END_TO_END_FLOW_INSTANCE:
				sequence_EndToEndFlowInstance(context, (EndToEndFlowInstance) semanticObject); 
				return; 
			case InstancePackage.FEATURE_INSTANCE:
				sequence_FeatureInstance(context, (FeatureInstance) semanticObject); 
				return; 
			case InstancePackage.FLOW_SPECIFICATION_INSTANCE:
				sequence_FlowSpecificationInstance(context, (FlowSpecificationInstance) semanticObject); 
				return; 
			case InstancePackage.MODE_INSTANCE:
				sequence_ModeInstance(context, (ModeInstance) semanticObject); 
				return; 
			case InstancePackage.MODE_TRANSITION_INSTANCE:
				sequence_ModeTransitionInstance(context, (ModeTransitionInstance) semanticObject); 
				return; 
			case InstancePackage.SYSTEM_INSTANCE:
				sequence_SystemInstance(context, (SystemInstance) semanticObject); 
				return; 
			case InstancePackage.SYSTEM_OPERATION_MODE:
				sequence_SystemOperationMode(context, (SystemOperationMode) semanticObject); 
				return; 
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (
	 *         category=ComponentCategory 
	 *         name=ID 
	 *         index+=LONG* 
	 *         (inMode+=[ModeInstance|ID] inMode+=[ModeInstance|ID]*)? 
	 *         subcomponent=[Subcomponent|SUBREF] 
	 *         (srcConnectionInstance+=[ConnectionInstance|CONNINSTREF] srcConnectionInstance+=[ConnectionInstance|CONNINSTREF]*)? 
	 *         (dstConnectionInstance+=[ConnectionInstance|CONNINSTREF] dstConnectionInstance+=[ConnectionInstance|CONNINSTREF]*)? 
	 *         (
	 *             featureInstance+=FeatureInstance | 
	 *             componentInstance+=ComponentInstance | 
	 *             connectionInstance+=ConnectionInstance | 
	 *             flowSpecification+=FlowSpecificationInstance | 
	 *             endToEndFlow+=EndToEndFlowInstance | 
	 *             modeInstance+=ModeInstance | 
	 *             modeTransitionInstance+=ModeTransitionInstance
	 *         )*
	 *     )
	 */
	protected void sequence_ComponentInstance(EObject context, ComponentInstance semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         complete?='complete'? 
	 *         kind=ConnectionKind 
	 *         name=STRING 
	 *         source=[ConnectionInstanceEnd|INSTANCEREF] 
	 *         bidirectional?='<->'? 
	 *         destination=[ConnectionInstanceEnd|INSTANCEREF] 
	 *         (inSystemOperationMode+=[SystemOperationMode|SOMREF] inSystemOperationMode+=[SystemOperationMode|SOMREF]*)? 
	 *         connectionReference+=ConnectionReference+
	 *     )
	 */
	protected void sequence_ConnectionInstance(EObject context, ConnectionInstance semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         source=[ConnectionInstanceEnd|INSTANCEREF] 
	 *         destination=[ConnectionInstanceEnd|INSTANCEREF] 
	 *         connection=[Connection|SUBREF] 
	 *         context=[ComponentInstance|INSTANCEREFWITHPARENT]
	 *     )
	 */
	protected void sequence_ConnectionReference(EObject context, ConnectionReference semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         (flowElement+=[FlowElementInstance|FLOWELEMENTREF] flowElement+=[FlowElementInstance|FLOWELEMENTREF]*)? 
	 *         (inSystemOperationMode+=[SystemOperationMode|SOMREF] inSystemOperationMode+=[SystemOperationMode|SOMREF]*)? 
	 *         endToEndFlow=[EndToEndFlow|SUBREF]
	 *     )
	 */
	protected void sequence_EndToEndFlowInstance(EObject context, EndToEndFlowInstance semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         direction=DirectionType 
	 *         category=FeatureCategory 
	 *         name=ID 
	 *         index=LONG? 
	 *         feature=[Feature|FEATREF] 
	 *         (
	 *             (srcConnectionInstance+=[ConnectionInstance|CONNINSTREF] | srcFlowSpec+=[FlowSpecificationInstance|ID]) 
	 *             (srcConnectionInstance+=[ConnectionInstance|CONNINSTREF] | srcFlowSpec+=[FlowSpecificationInstance|ID])*
	 *         )? 
	 *         (
	 *             (dstConnectionInstance+=[ConnectionInstance|CONNINSTREF] | dstFlowSpec+=[FlowSpecificationInstance|ID]) 
	 *             (dstConnectionInstance+=[ConnectionInstance|CONNINSTREF] | dstFlowSpec+=[FlowSpecificationInstance|ID])*
	 *         )? 
	 *         featureInstance+=FeatureInstance*
	 *     )
	 */
	protected void sequence_FeatureInstance(EObject context, FeatureInstance semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         source=[FeatureInstance|SIMPLEINSTANCEREF]? 
	 *         destination=[FeatureInstance|SIMPLEINSTANCEREF]? 
	 *         (inMode+=[ModeInstance|ID] inMode+=[ModeInstance|ID]*)? 
	 *         flowSpecification=[FlowSpecification|FEATREF]
	 *     )
	 */
	protected void sequence_FlowSpecificationInstance(EObject context, FlowSpecificationInstance semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         initial?='initial'? 
	 *         derived?='derived'? 
	 *         name=ID 
	 *         (parent+=[ModeInstance|ID] | (parent+=[ModeInstance|ID] parent+=[ModeInstance|ID]+))? 
	 *         (srcModeTransition+=[ModeTransitionInstance|SOMREF] srcModeTransition+=[ModeTransitionInstance|SOMREF]*)? 
	 *         (dstModeTransition+=[ModeTransitionInstance|SOMREF] dstModeTransition+=[ModeTransitionInstance|SOMREF]*)? 
	 *         mode=[Mode|MODEREF]
	 *     )
	 */
	protected void sequence_ModeInstance(EObject context, ModeInstance semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=MTNAME source=[ModeInstance|ID] destination=[ModeInstance|ID] modeTransition=[ModeTransition|MODEREF])
	 */
	protected void sequence_ModeTransitionInstance(EObject context, ModeTransitionInstance semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         category=ComponentCategory 
	 *         name=ID 
	 *         componentImplementation=[ComponentImplementation|IMPLREF] 
	 *         (
	 *             featureInstance+=FeatureInstance | 
	 *             componentInstance+=ComponentInstance | 
	 *             connectionInstance+=ConnectionInstance | 
	 *             flowSpecification+=FlowSpecificationInstance | 
	 *             endToEndFlow+=EndToEndFlowInstance | 
	 *             modeInstance+=ModeInstance | 
	 *             modeTransitionInstance+=ModeTransitionInstance | 
	 *             systemOperationMode+=SystemOperationMode
	 *         )*
	 *     )
	 */
	protected void sequence_SystemInstance(EObject context, SystemInstance semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=STRING (currentMode+=[ModeInstance|MODEINSTREF] currentMode+=[ModeInstance|MODEINSTREF]*)?)
	 */
	protected void sequence_SystemOperationMode(EObject context, SystemOperationMode semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
/*
 *
 * <copyright>
 * Copyright  2012 by Carnegie Mellon University, all rights reserved.
 *
 * Use of the Open Source AADL Tool Environment (OSATE) is subject to the terms of the license set forth
 * at http://www.eclipse.org/org/documents/epl-v10.html.
 *
 * NO WARRANTY
 *
 * ANY INFORMATION, MATERIALS, SERVICES, INTELLECTUAL PROPERTY OR OTHER PROPERTY OR RIGHTS GRANTED OR PROVIDED BY
 * CARNEGIE MELLON UNIVERSITY PURSUANT TO THIS LICENSE (HEREINAFTER THE "DELIVERABLES") ARE ON AN "AS-IS" BASIS.
 * CARNEGIE MELLON UNIVERSITY MAKES NO WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED AS TO ANY MATTER INCLUDING,
 * BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR A PARTICULAR PURPOSE, MERCHANTABILITY, INFORMATIONAL CONTENT,
 * NONINFRINGEMENT, OR ERROR-FREE OPERATION. CARNEGIE MELLON UNIVERSITY SHALL NOT BE LIABLE FOR INDIRECT, SPECIAL OR
 * CONSEQUENTIAL DAMAGES, SUCH AS LOSS OF PROFITS OR INABILITY TO USE SAID INTELLECTUAL PROPERTY, UNDER THIS LICENSE,
 * REGARDLESS OF WHETHER SUCH PARTY WAS AWARE OF THE POSSIBILITY OF SUCH DAMAGES. LICENSEE AGREES THAT IT WILL NOT
 * MAKE ANY WARRANTY ON BEHALF OF CARNEGIE MELLON UNIVERSITY, EXPRESS OR IMPLIED, TO ANY PERSON CONCERNING THE
 * APPLICATION OF OR THE RESULTS TO BE OBTAINED WITH THE DELIVERABLES UNDER THIS LICENSE.
 *
 * Licensee hereby agrees to defend, indemnify, and hold harmless Carnegie Mellon University, its trustees, officers,
 * employees, and agents from all claims or demands made against them (and any related losses, expenses, or
 * attorney's fees) arising out of, or relating to Licensee's and/or its sub licensees' negligent use or willful
 * misuse of or negligent conduct or willful misconduct regarding the Software, facilities, or other rights or
 * assistance granted by Carnegie Mellon University under this License, including, but not limited to, any claims of
 * product liability, personal injury, death, damage to property, or violation of any laws or regulations.
 *
 * Carnegie Mellon Carnegie Mellon University Software Engineering Institute authored documents are sponsored by the U.S. Department
 * of Defense under Contract F19628-00-C-0003. Carnegie Mellon University retains copyrights in all material produced
 * under this contract. The U.S. Government retains a non-exclusive, royalty-free license to publish or reproduce these
 * documents, or allow others to do so, for U.S. Government purposes only pursuant to the copyright license
 * under the contract clause at 252.227.7013.
 * </copyright>
 */

package org.osate.xtext.aadl2.ui.quickfix;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.ui.editor.model.edit.IModification
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification
import org.eclipse.xtext.ui.editor.quickfix.Fix
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor
import org.eclipse.xtext.validation.Issue
import org.osate.aadl2.Feature
import org.osate.aadl2.NamedElement
import org.osate.xtext.aadl2.properties.ui.quickfix.PropertiesQuickfixProvider
import org.osate.xtext.aadl2.validation.Aadl2JavaValidator

public class Aadl2QuickfixProvider extends PropertiesQuickfixProvider {
	/**
	 * QuickFix for matching the defining and ending identifiers of classifiers, packages, and property sets.
	 * The issue data array is expected to have three elements:
	 *
	 * issue.getData()[0]: The defining identifier of the classifier or model unit.
	 * issue.getData()[1]: The ending identifier of the classifier or model unit.
	 * issue.getData()[2]: The offset of the ending identifier within the Xtext document.
	 */
	@Fix(Aadl2JavaValidator.MISMATCHED_BEGINNING_AND_ENDING_IDENTIFIERS)
	def public void fixMismatchedBeginningAndEndingIdentifiers(Issue issue, IssueResolutionAcceptor acceptor) {
		val String beginningName = issue.getData().get(0);
		val String endingName = issue.getData().get(1);
		val int endingIdentifierOffset = Integer.parseInt(issue.getData().get(2));
		acceptor.accept(issue, "Change defining identifier to '" + endingName + "'", null, null,
				new ISemanticModification() {
					override public void apply(EObject element, IModificationContext context) throws Exception {
						(element as NamedElement).setName(endingName);
					}
				});
		acceptor.accept(issue, "Change ending identifier to '" + beginningName + "'", null, null, new IModification() {
			override public void apply(IModificationContext context) throws Exception {
				context.getXtextDocument().replace(endingIdentifierOffset, endingName.length(), beginningName);
			}
		});
	}

	/**
	 * QuickFix for Non-unique ComponentType names, most likely "refined to" missing.
	 * The issue data array is expected to have three elements:
	 *
	 * issue.getData()[0]: The name of the element.
	 * issue.getData()[1]: The offset of the ":".
	 * issue.getData()[2]: replacement value.
	 */
	@Fix(Aadl2JavaValidator.DUPLICATE_COMPONENT_TYPE_NAME)
	def public void fixDuplicateComponentType(Issue issue, IssueResolutionAcceptor acceptor) {
		val String componentTypeName = issue.getData().get(0);
		val int offset = Integer.parseInt(issue.getData().get(1));
		val String replacementVal = issue.getData().get(2);
		
		acceptor.accept(issue, "Add 'refined to' to '" + componentTypeName + "'", null, null, new IModification() {
			override public void apply(IModificationContext context) throws Exception {
				context.getXtextDocument().replace(offset, 1, replacementVal);
			} 
		});
	}

}
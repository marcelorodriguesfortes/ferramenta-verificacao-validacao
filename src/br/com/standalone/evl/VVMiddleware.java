
package br.com.standalone.evl;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.epsilon.emc.emf.EmfUtil;
import org.eclipse.epsilon.eol.IEolExecutableModule;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.EvlModule;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;

import br.com.standalone.EpsilonStandalone;

public class VVMiddleware extends EpsilonStandalone {
	private String ModeloSynthesis;
	private String ModeloController;
	private String ModeloBroker;
	private String ModeloRelacionamentoSynthesis;
	private String ModeloRelacionamentoController;
	private String ModeloRelacionamentoBroker;
	private String FeatureModel;	
	private String msg = null;

	
	

	public static void main(String[] args) throws Exception {
		new VVMiddleware().execute();
	}
	
	@Override
	public IEolExecutableModule createModule() {
		return new EvlModule();
	}

	@Override
	public List<IModel> getModels() throws Exception {
		List<IModel> models = new ArrayList<IModel>();
		
		Map<String, String> env = System.getenv();
		
		EmfUtil.register(URI.createURI(env.get("CAMINHO_METAMODELO_BROKERD4")), EPackage.Registry.INSTANCE);
		EmfUtil.register(URI.createURI(env.get("CAMINHO_METAMODELO_SYNTHESISD4")), EPackage.Registry.INSTANCE);
		EmfUtil.register(URI.createURI(env.get("CAMINHO_METAMODELO_CONTROLLERD4")), EPackage.Registry.INSTANCE);
		EmfUtil.register(URI.createURI(env.get("CAMINHO_METAMODELO_FEATURED4")), EPackage.Registry.INSTANCE);
		EmfUtil.register(URI.createURI(env.get("CAMINHO_METAMODELO_RELACIONAMENTOD4")), EPackage.Registry.INSTANCE);
		
		models.add(createEmfModelByURI("FeatureModel", "file:/" + getFeatureModel(), "http://metamodelofeature", true, true));
		 
		models.add(createEmfModelByURI("MetamodeloCamadaSynthesis", env.get("CAMINHO_METAMODELO_SYNTHESISD4") , "http://www.eclipse.org/emf/2002/Ecore", true, true));
		models.add(createEmfModelByURI("MetamodeloCamadaController", env.get("CAMINHO_METAMODELO_CONTROLLERD4"), "http://www.eclipse.org/emf/2002/Ecore", true, true));
	    models.add(createEmfModelByURI("MetamodeloCamadaBroker", env.get("CAMINHO_METAMODELO_BROKERD4"), "http://www.eclipse.org/emf/2002/Ecore", true, true));
	    
		models.add(createEmfModelByURI("RelacionamentoSynthesis", "file:/" +  getModeloRelacionamentoSynthesis() , "http://metamodelorelacionamento", true, true));
		models.add(createEmfModelByURI("RelacionamentoController", "file:/" + getModeloRelacionamentoController() , "http://metamodelorelacionamento", true, true));
		models.add(createEmfModelByURI("RelacionamentoBroker","file:/" +  getModeloRelacionamentoBroker() , "http://metamodelorelacionamento", true, true));
		
		
		models.add(createEmfModelByURI("Synthesis","file:/" +  getModeloSynthesis(), "http://metamodelosynthesis", true, true));
		models.add(createEmfModelByURI("Controller", "file:/" + getModeloController(), "http://metamodelocontroller", true, true));
		models.add(createEmfModelByURI("Broker", "file:/" + getModeloBroker(), "http://metamodelobroker", true, true));
        
		
	
		return models;	
	}

	@Override
	public String getSource() throws Exception {
		return "evl/Restricoes.evl";
	}
	
	
	@Override
	public void postProcess() {
		
		EvlModule module = (EvlModule) this.module;
		
		Collection<UnsatisfiedConstraint> unsatisfied = module.getContext().getUnsatisfiedConstraints();
	
		if (unsatisfied.size() > 0) {
			//System.err.println("Algumas restrições não foram satisfeitas");
			for (UnsatisfiedConstraint uc : unsatisfied) {
				//System.out.println(uc.getMessage());
				setMsg(uc.getMessage() + " \n");
			}
		}
		else {
			//System.out.println("Todas as restricoes foram satisfeitas!");
			setMsg("Todas as restriçoes foram satisfeitas!");
		}
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		if(this.msg != null)
			this.msg = this.msg + msg;
			//this.msg = this.msg + "\n" + msg;
		else
			this.msg = msg;
	}

	public String getModeloRelacionamentoSynthesis() {
		return ModeloRelacionamentoSynthesis;
	}

	public void setModeloRelacionamentoSynthesis(String modeloRelacionamentoSynthesis) {
		ModeloRelacionamentoSynthesis = modeloRelacionamentoSynthesis;
	}

	public String getModeloRelacionamentoController() {
		return ModeloRelacionamentoController;
	}

	public void setModeloRelacionamentoController(String string) {
		ModeloRelacionamentoController = string;
	}

	public String getModeloRelacionamentoBroker() {
		return ModeloRelacionamentoBroker;
	}

	public void setModeloRelacionamentoBroker(String modeloRelacionamentoBroker) {
		ModeloRelacionamentoBroker = modeloRelacionamentoBroker;
	}
	public String getModeloSynthesis() {
		return ModeloSynthesis;
	}

	public void setModeloSynthesis(String modeloSynthesis) {
		ModeloSynthesis = modeloSynthesis;
	}

	public String getModeloController() {
		return ModeloController;
	}

	public void setModeloController(String modeloController) {
		ModeloController = modeloController;
	}

	public String getModeloBroker() {
		return ModeloBroker;
	}

	public void setModeloBroker(String modeloBroker) {
		ModeloBroker = modeloBroker;
	}

	public String getFeatureModel() {
		return FeatureModel;
	}

	public void setFeatureModel(String featureModel) {
		FeatureModel = featureModel;
	}
	
}

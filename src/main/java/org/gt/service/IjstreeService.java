package org.gt.service;
import org.gt.vo.jstree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IjstreeService {
	List<jstree>  model_tree();
	List<jstree>  role_tree();
	List<jstree>  modelfunc_tree();
}

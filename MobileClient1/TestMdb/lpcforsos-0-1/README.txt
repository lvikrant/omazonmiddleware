 _      ____    ____   __               ____    ___   ____  
| |    |  _ \  / ___| / _|  ___   _ __ / ___|  / _ \ / ___| 
| |    | |_) || |    | |_  / _ \ | '__|\___ \ | | | |\___ \ 
| |___ |  __/ | |___ |  _|| (_) || |    ___) || |_| | ___) |
|_____||_|     \____||_|   \___/ |_|   |____/  \___/ |____/ 
                                                            

LPCforSOS (Learning by Pairwise Comparisons for Structured Output Spaces) is a 
machine learning framework with a special focus on structured output spaces 
and pairwise learning. It supports currently multiclass, ordinal, 
hierarchical, multi-label and label ranking classification settings. 

20.08.11 Project Status : alpha, Version 0.1 

Getting Started: 
----------------
You can run an experiment as configured in config.xml by calling:
  "java -cp lpcforsos.jar:libs/* lpcforsos.evaluation.Evaluation" 

Some example configs for different problem types are included:
config_labelranking.xml, config_ordinal.xml
You can run the experiment by calling:
  "java -cp lpcforsos.jar:libs/* lpcforsos.evaluation.Evaluation config_labelranking.xml" 


TODO: 
---------------
 - general documentation
 - multilabel methods still buggy

Framework Homepage: http://sourceforge.net/projects/lpcforsos
More Information at: http://www.ke.tu-darmstadt.de/projects/lpcforsos

Technical University Darmstadt, 
Knowledge Engineering Group Darmstadt, Germany,
http://www.ke.tu-darmstadt.de 

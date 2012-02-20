var labelType, useGradients, nativeTextSupport, animate;
(function() { 
var ua = navigator.userAgent, 
iStuff = ua.match(/iPhone/i) || ua.match(/iPad/i),
typeOfCanvas = typeof HTMLCanvasElement,
nativeCanvasSupport = (typeOfCanvas == 'object' || typeOfCanvas == 'function'),
textSupport = nativeCanvasSupport 
  && (typeof document.createElement('canvas').getContext('2d').fillText == 'function'); 
labelType = (!nativeCanvasSupport || (textSupport && !iStuff))? 'Native' : 'HTML';
nativeTextSupport = labelType == 'Native'; 
useGradients = nativeCanvasSupport;animate = !(iStuff || !nativeCanvasSupport); 
})();
var Log = {
elem: false, 
write: function(text){ 
if (!this.elem)  
this.elem = document.getElementById('log'); 
this.elem.innerHTML = text; 
this.elem.style.left = (500 - this.elem.offsetWidth / 2) + 'px'; 
} 
}; 
function init(){ 
 var json = [ 
{
 'adjacencies': [ 
 { 
'nodeTo':'Acetaldehyde', 
'nodeFrom':'aldehyde dehydrogenase  NADplus ', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'aldehyde dehydrogenase  NADplus ',
'name': 'aldehyde dehydrogenase  NADplus '
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Acetaldehyde',
'name': 'Acetaldehyde'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'Acetate', 
'nodeFrom' :'aldehyde dehydrogenase  NADplus ', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'aldehyde dehydrogenase  NADplus ',
'name': 'aldehyde dehydrogenase  NADplus '
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Acetate',
'name': 'Acetate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'ß-D-Fructose 1 6-bisphosphate', 
'nodeFrom':'fructose-bisphosphatase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'fructose-bisphosphatase',
'name': 'fructose-bisphosphatase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'ß-D-Fructose 1 6-bisphosphate',
'name': 'ß-D-Fructose 1 6-bisphosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'ß-D-Fructose 6-phosphate', 
'nodeFrom' :'fructose-bisphosphatase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'fructose-bisphosphatase',
'name': 'fructose-bisphosphatase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'ß-D-Fructose 6-phosphate',
'name': 'ß-D-Fructose 6-phosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'α-D-Glucose', 
'nodeFrom':'aldose 1-epimerase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'aldose 1-epimerase',
'name': 'aldose 1-epimerase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'α-D-Glucose',
'name': 'α-D-Glucose'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'ß-D-Glucose', 
'nodeFrom' :'aldose 1-epimerase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'aldose 1-epimerase',
'name': 'aldose 1-epimerase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'ß-D-Glucose',
'name': 'ß-D-Glucose'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'α-D-Glucose 6-phosphate', 
'nodeFrom':'glucose-6-phosphate isomerase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'glucose-6-phosphate isomerase',
'name': 'glucose-6-phosphate isomerase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'α-D-Glucose 6-phosphate',
'name': 'α-D-Glucose 6-phosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'ß-D-Glucose 6-phosphate', 
'nodeFrom' :'glucose-6-phosphate isomerase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'glucose-6-phosphate isomerase',
'name': 'glucose-6-phosphate isomerase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'ß-D-Glucose 6-phosphate',
'name': 'ß-D-Glucose 6-phosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':' 2R -2-Hydroxy-3- phosphonooxy -propanal', 
'nodeFrom':'triose-phosphate isomerase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'triose-phosphate isomerase',
'name': 'triose-phosphate isomerase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': ' 2R -2-Hydroxy-3- phosphonooxy -propanal',
'name': ' 2R -2-Hydroxy-3- phosphonooxy -propanal'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'Glycerone phosphate', 
'nodeFrom' :'triose-phosphate isomerase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'triose-phosphate isomerase',
'name': 'triose-phosphate isomerase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Glycerone phosphate',
'name': 'Glycerone phosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':' 2- α-Hydroxyethyl thiamine diphosphate', 
'nodeFrom':'pyruvate decarboxylase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'pyruvate decarboxylase',
'name': 'pyruvate decarboxylase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': ' 2- α-Hydroxyethyl thiamine diphosphate',
'name': ' 2- α-Hydroxyethyl thiamine diphosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'Acetaldehyde', 
'nodeFrom' :'pyruvate decarboxylase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'pyruvate decarboxylase',
'name': 'pyruvate decarboxylase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Acetaldehyde',
'name': 'Acetaldehyde'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'Acetate', 
'nodeFrom':'acetate-CoA ligase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'acetate-CoA ligase',
'name': 'acetate-CoA ligase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Acetate',
'name': 'Acetate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'Acetyl-CoA', 
'nodeFrom' :'acetate-CoA ligase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'acetate-CoA ligase',
'name': 'acetate-CoA ligase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Acetyl-CoA',
'name': 'Acetyl-CoA'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'ß-D-Glucose', 
'nodeFrom':'hexokinase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'hexokinase',
'name': 'hexokinase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'ß-D-Glucose',
'name': 'ß-D-Glucose'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'ß-D-Glucose 6-phosphate', 
'nodeFrom' :'hexokinase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'hexokinase',
'name': 'hexokinase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'ß-D-Glucose 6-phosphate',
'name': 'ß-D-Glucose 6-phosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'ß-D-Fructose 1 6-bisphosphate', 
'nodeFrom':'fructose-bisphosphate aldolase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'fructose-bisphosphate aldolase',
'name': 'fructose-bisphosphate aldolase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'ß-D-Fructose 1 6-bisphosphate',
'name': 'ß-D-Fructose 1 6-bisphosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':' 2R -2-Hydroxy-3- phosphonooxy -propanal', 
'nodeFrom' :'fructose-bisphosphate aldolase', 
'data':{} 
},
{ 
'nodeTo':'Glycerone phosphate', 
'nodeFrom' :'fructose-bisphosphate aldolase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'fructose-bisphosphate aldolase',
'name': 'fructose-bisphosphate aldolase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': ' 2R -2-Hydroxy-3- phosphonooxy -propanal',
'name': ' 2R -2-Hydroxy-3- phosphonooxy -propanal'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Glycerone phosphate',
'name': 'Glycerone phosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':' 2-Phospho-D-glycerate', 
'nodeFrom':'phosphoglycerate mutase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'phosphoglycerate mutase',
'name': 'phosphoglycerate mutase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': ' 2-Phospho-D-glycerate',
'name': ' 2-Phospho-D-glycerate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':' 3-Phospho-D-glycerate', 
'nodeFrom' :'phosphoglycerate mutase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'phosphoglycerate mutase',
'name': 'phosphoglycerate mutase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': ' 3-Phospho-D-glycerate',
'name': ' 3-Phospho-D-glycerate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':' 3-Phospho-D-glycerate', 
'nodeFrom':'phosphoglycerate kinase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'phosphoglycerate kinase',
'name': 'phosphoglycerate kinase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': ' 3-Phospho-D-glycerate',
'name': ' 3-Phospho-D-glycerate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':' 3-Phospho-D-glyceroyl phosphate', 
'nodeFrom' :'phosphoglycerate kinase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'phosphoglycerate kinase',
'name': 'phosphoglycerate kinase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': ' 3-Phospho-D-glyceroyl phosphate',
'name': ' 3-Phospho-D-glyceroyl phosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'α-D-Glucose 6-phosphate', 
'nodeFrom':'glucose-6-phosphate isomerase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'glucose-6-phosphate isomerase',
'name': 'glucose-6-phosphate isomerase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'α-D-Glucose 6-phosphate',
'name': 'α-D-Glucose 6-phosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'ß-D-Fructose 6-phosphate', 
'nodeFrom' :'glucose-6-phosphate isomerase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'glucose-6-phosphate isomerase',
'name': 'glucose-6-phosphate isomerase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'ß-D-Fructose 6-phosphate',
'name': 'ß-D-Fructose 6-phosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'D-Glucose 1-phosphate', 
'nodeFrom':'phosphoglucomutase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'phosphoglucomutase',
'name': 'phosphoglucomutase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'D-Glucose 1-phosphate',
'name': 'D-Glucose 1-phosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'α-D-Glucose 6-phosphate', 
'nodeFrom' :'phosphoglucomutase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'phosphoglucomutase',
'name': 'phosphoglucomutase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'α-D-Glucose 6-phosphate',
'name': 'α-D-Glucose 6-phosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':' S -Lactate', 
'nodeFrom':'L-lactate dehydrogenase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'L-lactate dehydrogenase',
'name': 'L-lactate dehydrogenase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': ' S -Lactate',
'name': ' S -Lactate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'Pyruvate', 
'nodeFrom' :'L-lactate dehydrogenase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'L-lactate dehydrogenase',
'name': 'L-lactate dehydrogenase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Pyruvate',
'name': 'Pyruvate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':' 2- α-Hydroxyethyl thiamine diphosphate', 
'nodeFrom':'pyruvate dehydrogenase  acetyl-transferring ', 
'data':{} 
}, 
{ 
'nodeTo':'Lipoamide', 
'nodeFrom':'pyruvate dehydrogenase  acetyl-transferring ', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'pyruvate dehydrogenase  acetyl-transferring ',
'name': 'pyruvate dehydrogenase  acetyl-transferring '
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': ' 2- α-Hydroxyethyl thiamine diphosphate',
'name': ' 2- α-Hydroxyethyl thiamine diphosphate'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Lipoamide',
'name': 'Lipoamide'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'Thiamin diphosphate', 
'nodeFrom' :'pyruvate dehydrogenase  acetyl-transferring ', 
'data':{} 
},
{ 
'nodeTo':'S-Acetyldihydrolipoamide', 
'nodeFrom' :'pyruvate dehydrogenase  acetyl-transferring ', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'pyruvate dehydrogenase  acetyl-transferring ',
'name': 'pyruvate dehydrogenase  acetyl-transferring '
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Thiamin diphosphate',
'name': 'Thiamin diphosphate'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'S-Acetyldihydrolipoamide',
'name': 'S-Acetyldihydrolipoamide'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'Ethanol', 
'nodeFrom':'alcohol dehydrogenase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'alcohol dehydrogenase',
'name': 'alcohol dehydrogenase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Ethanol',
'name': 'Ethanol'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'Acetaldehyde', 
'nodeFrom' :'alcohol dehydrogenase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'alcohol dehydrogenase',
'name': 'alcohol dehydrogenase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Acetaldehyde',
'name': 'Acetaldehyde'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'Lipoamide', 
'nodeFrom':'dihydrolipoyl dehydrogenanse', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'dihydrolipoyl dehydrogenanse',
'name': 'dihydrolipoyl dehydrogenanse'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Lipoamide',
'name': 'Lipoamide'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'Dihydrolipoamide', 
'nodeFrom' :'dihydrolipoyl dehydrogenanse', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'dihydrolipoyl dehydrogenanse',
'name': 'dihydrolipoyl dehydrogenanse'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Dihydrolipoamide',
'name': 'Dihydrolipoamide'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'α-D-Glucose', 
'nodeFrom':'hexokinase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'hexokinase',
'name': 'hexokinase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'α-D-Glucose',
'name': 'α-D-Glucose'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'α-D-Glucose 6-phosphate', 
'nodeFrom' :'hexokinase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'hexokinase',
'name': 'hexokinase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'α-D-Glucose 6-phosphate',
'name': 'α-D-Glucose 6-phosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'Acetyl-CoA', 
'nodeFrom':'dihydrolipoyllysine-residue acetyltransferase', 
'data':{} 
}, 
{ 
'nodeTo':'Dihydrolipoamide', 
'nodeFrom':'dihydrolipoyllysine-residue acetyltransferase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'dihydrolipoyllysine-residue acetyltransferase',
'name': 'dihydrolipoyllysine-residue acetyltransferase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Acetyl-CoA',
'name': 'Acetyl-CoA'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Dihydrolipoamide',
'name': 'Dihydrolipoamide'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'S-Acetyldihydrolipoamide', 
'nodeFrom' :'dihydrolipoyllysine-residue acetyltransferase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'dihydrolipoyllysine-residue acetyltransferase',
'name': 'dihydrolipoyllysine-residue acetyltransferase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'S-Acetyldihydrolipoamide',
'name': 'S-Acetyldihydrolipoamide'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'Phosphoenolpyruvate', 
'nodeFrom':'pyruvate kinase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'pyruvate kinase',
'name': 'pyruvate kinase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Phosphoenolpyruvate',
'name': 'Phosphoenolpyruvate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'Pyruvate', 
'nodeFrom' :'pyruvate kinase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'pyruvate kinase',
'name': 'pyruvate kinase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Pyruvate',
'name': 'Pyruvate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'ß-D-Glucose 6-phosphate', 
'nodeFrom':'glucose-6-phosphate isomerase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'glucose-6-phosphate isomerase',
'name': 'glucose-6-phosphate isomerase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'ß-D-Glucose 6-phosphate',
'name': 'ß-D-Glucose 6-phosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'ß-D-Fructose 6-phosphate', 
'nodeFrom' :'glucose-6-phosphate isomerase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'glucose-6-phosphate isomerase',
'name': 'glucose-6-phosphate isomerase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'ß-D-Fructose 6-phosphate',
'name': 'ß-D-Fructose 6-phosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':' 2-Phospho-D-glycerate', 
'nodeFrom':'phosphopyruvate hydratase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'phosphopyruvate hydratase',
'name': 'phosphopyruvate hydratase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': ' 2-Phospho-D-glycerate',
'name': ' 2-Phospho-D-glycerate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'Phosphoenolpyruvate', 
'nodeFrom' :'phosphopyruvate hydratase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'phosphopyruvate hydratase',
'name': 'phosphopyruvate hydratase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Phosphoenolpyruvate',
'name': 'Phosphoenolpyruvate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':' 2- α-Hydroxyethyl thiamine diphosphate', 
'nodeFrom':'pyruvate dehydrogenase  acetyl-transferring  & pyruvate decarboxylase', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'pyruvate dehydrogenase  acetyl-transferring  & pyruvate decarboxylase',
'name': 'pyruvate dehydrogenase  acetyl-transferring  & pyruvate decarboxylase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': ' 2- α-Hydroxyethyl thiamine diphosphate',
'name': ' 2- α-Hydroxyethyl thiamine diphosphate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':'Thiamin diphosphate', 
'nodeFrom' :'pyruvate dehydrogenase  acetyl-transferring  & pyruvate decarboxylase', 
'data':{} 
},
{ 
'nodeTo':'Pyruvate', 
'nodeFrom' :'pyruvate dehydrogenase  acetyl-transferring  & pyruvate decarboxylase', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'pyruvate dehydrogenase  acetyl-transferring  & pyruvate decarboxylase',
'name': 'pyruvate dehydrogenase  acetyl-transferring  & pyruvate decarboxylase'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Thiamin diphosphate',
'name': 'Thiamin diphosphate'
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': 'Pyruvate',
'name': 'Pyruvate'
},
{
 'adjacencies': [ 
 { 
'nodeTo':' 2R -2-Hydroxy-3- phosphonooxy -propanal', 
'nodeFrom':'glyceraldehyde-3-phosphate dehydrogenase  phosphorylating ', 
'data':{} 
}, 
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'glyceraldehyde-3-phosphate dehydrogenase  phosphorylating ',
'name': 'glyceraldehyde-3-phosphate dehydrogenase  phosphorylating '
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': ' 2R -2-Hydroxy-3- phosphonooxy -propanal',
'name': ' 2R -2-Hydroxy-3- phosphonooxy -propanal'
},
{
 'adjacencies': [ 
 { 
'nodeTo':' 3-Phospho-D-glyceroyl phosphate', 
'nodeFrom' :'glyceraldehyde-3-phosphate dehydrogenase  phosphorylating ', 
'data':{} 
},
], 
'data': { 
'$color': '#C74243', '$type': 'triangle', 
 '$dim': 15 
}, 
'id': 'glyceraldehyde-3-phosphate dehydrogenase  phosphorylating ',
'name': 'glyceraldehyde-3-phosphate dehydrogenase  phosphorylating '
},
{
 'adjacencies': [ ], 
 'data': { 
'$color': '#416D9C', '$type': 'square', 
 '$dim': 15 
}, 
'id': ' 3-Phospho-D-glyceroyl phosphate',
'name': ' 3-Phospho-D-glyceroyl phosphate'
},
  ];
var fd = new $jit.ForceDirected({
injectInto: 'infovis',
Navigation: {
enable: true,
panning: 'avoid nodes',
zooming: 10 
  },
Node: {
overridable: true
},
Edge: {
overridable: true,
color: '#23A4FF',
lineWidth: 0.4
},
Label: {
type: labelType,
 size: 10,
style: 'bold'
},
 Tips: {
 enable: true,
 onShow: function(tip, node) {
  var count = 0;
node.eachAdjacency(function() { count++; });
tip.innerHTML = '<div class= \'tip-title\'>' + node.name + '</div>'+'<div class=\'tip-text\'><b>connections:</b> ' + count + '</div>';
}
},
Events: {
enable: true,
type: 'Native',
onMouseEnter: function() {
fd.canvas.getElement().style.cursor = 'move';
},
onMouseLeave: function() {
fd.canvas.getElement().style.cursor = '';
},
onDragMove: function(node, eventInfo, e) {
var pos = eventInfo.getPos();
node.pos.setc(pos.x, pos.y);
fd.plot();
 },
onTouchMove: function(node, eventInfo, e) {
$jit.util.event.stop(e); 
 this.onDragMove(node, eventInfo, e);
},
onClick: function(node) {
if(!node) return;
 var html = '<h4>' + node.name + '</h4><b> connections:</b><ul><li>',
 list = [];
node.eachAdjacency(function(adj){
 list.push(adj.nodeTo.name);
 });
$jit.id('inner-details').innerHTML = html + list.join('</li><li>') + '</li></ul>';
}
},
iterations: 200,
levelDistance: 130,
onCreateLabel: function(domElement, node){
domElement.innerHTML = node.name;
var style = domElement.style;
style.fontSize = '0.8em';
style.color = '#ddd';
},
onPlaceLabel: function(domElement, node){
var style = domElement.style;
var left = parseInt(style.left);
var top = parseInt(style.top);
var w = domElement.offsetWidth;
style.left = (left - w / 2) + 'px';
style.top = (top + 10) + 'px';
style.display = '';
}
});
fd.loadJSON(json);
fd.computeIncremental({
iter: 40,
property: 'end',
onStep: function(perc){
Log.write(perc + '% loaded...');
},
onComplete: function(){
Log.write('done');
fd.animate({
modes: ['linear'],
transition: $jit.Trans.Elastic.easeOut,
duration: 2500
});
}
});
}

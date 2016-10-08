in (layout 1) vec3 nc;
in vec4 n_nc;

out vec4 colorOut;

void main(void){
	//colorOut = vec4(nc.xyz, 1.0);
	colorOut = n_nc;
}
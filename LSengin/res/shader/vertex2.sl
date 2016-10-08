layout (location = 1) in vec3 position;

out vec3 nc;
out vec4 n_nc;

void main(void){
	gl_Position = vec4(position, 1.0);
	//n_nc = vec4(0.0, 0.0, 1.0, 1.0);
}
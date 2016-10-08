layout (location = 1) in vec3 position;

out vec4 nc;

void main(void){
	gl_Position = vec4(position, 1.0);
	nc = vec4(position.x+0.5, 1.0, position.y+0.5, 1.0);
}
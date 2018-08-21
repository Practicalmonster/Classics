ArrayList<vert> vert_container;
PFont Font1;
boolean Test;
boolean edit;
int l;
float Area;
void setup() {
  Font1 = createFont("Arial Bold", 24);
  Area = 0;
  edit = false;
  Test = false;
  vert_container = new ArrayList<vert>();
  size(600, 600) ;
}

void keyPressed() {
  if (key == 'e')
    if (edit)edit = false;
    else edit = true;
  if (key == 'a')
    Area =area(vert_container);
}

void mousePressed() {
  if (!Test || !edit)
  {
    vert h;
    h = new vert();
    vert_container.add(h);
  } else {
    vert_container.get(l).x = mouseX;
    vert_container.get(l).y = mouseY;
  }
}

void draw_verts() {
  for (vert B : vert_container) {
    noStroke();
    fill(0, 255, 0);
    ellipse(B.x, B.y, 10, 10);
  }
}
float Cross_product(vert x, vert y) {
  return (x.x*y.y- x.y*y.x)/2;
}

float area(ArrayList<vert> x) {
  float sum=0;
  int lmost = left_most();
  for (int i = lmost; i < lmost + x.size(); i++) {
    sum = sum + Cross_product(x.get(i%x.size()), x.get((i+1)%x.size()));
  }
  return sum;
}
int left_most(){
  float lmost =width;
  int k =0;
  for(int j = 0 ; j < vert_container.size(); j++){
    if(vert_container.get(j).x < lmost)k = j;
  }
  return k;
}

void draw_poly() {
  for (int i = 0; i < vert_container.size()-1; i++) {
    stroke(255, 0, 0);
    strokeWeight(3);
    line(vert_container.get(i).x, vert_container.get(i).y, vert_container.get(i+1).x, vert_container.get(i+1).y);
  }
  if (vert_container.size()-1 > 1)line(vert_container.get(0).x, vert_container.get(0).y, vert_container.get(vert_container.size()-1).x, vert_container.get(vert_container.size()-1).y);
}

void test_vert() {
  for (int i = 0; i < vert_container.size(); i++) {
    if (sqrt(pow(vert_container.get(i).x - mouseX, 2)- pow(vert_container.get(i).y - mouseY, 2)) <= 10) {
      Test = true;
      l = i;
    }
  }
}

void Grid() {
  for (int i = 0; i < height; i = i+50) {
    for (int j = 0; j < width; j = j +50) {
      noFill();
      stroke(255);
      strokeWeight(1);
      rect(j, i, 50, 50);
    }
  }
}

void Draw_text() {
  //verts text
  for (vert b : vert_container) {
    String X;
    X= "("+b.x/50+","+b.y/50+")";
    textSize(15);
    fill(0, 255, 0);
    text(X, b.x+10, b.y+10);
  }
  if (Area != 0) {
    String X = "Area = "+abs(Area/(50*50));
    textFont(Font1);
    fill(225, 115, 115);
    text(X, width -200, height - 50);
  }
}

void draw() {
  background(0);
  Grid();
  draw_verts();
  draw_poly();
  test_vert();
  Draw_text();
}
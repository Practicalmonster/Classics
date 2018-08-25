#include<iostream>
#include<algorithm>
#include <unordered_map>
#include<limits>
#include<vector>

using namespace std;

class graph{

	unordered_map<char , const unordered_map<char, int>> adjac;


public:
	void add_vertex(char node ,const unordered_map<char, int>& adj){
		adjac.insert(unordered_map<char, const unordered_map<char,int>>::value_type(node , adj));
	}


	vector<char> solve(char source , char reciever){
		vector<char> path;
		unordered_map<char, char> trailed;
		unordered_map<char, int> distances;
		vector<char> nodes;

		auto compare = [&](char a , char b){return distances[a] > distances[b];};

		//set all nodes but the source to infinity
		for(auto a:adjac)
		{
			if(a.first == source)
				distances[source] =0;
			else
				distances[a.first]=numeric_limits<int>::max();
			nodes.push_back(a.first);
			push_heap(begin(nodes),end(nodes),compare);
		}

		while(!nodes.empty())
		{
			pop_heap(begin(nodes),end(nodes), compare);
			char current = nodes.back();
			nodes.pop_back();

			if(current == reciever){
				//output path
				char f=reciever;
				while(trailed.find(current) != end(trailed)){
					path.push_back(current);
					current = trailed[current];
				}
				path.push_back(source);
				break;
			}


			if(distances[current] == numeric_limits<int>::max())break;


			//actuall process
			//for all adjacent nodes V. update distance if it's shorter
			for(auto& v : adjac[current]){
				if(distances[v.first] > distances[current] + v.second)
					{
						distances[v.first]=distances[current] + v.second;
						trailed[v.first] = current;
						nodes.push_back(v.first);
						push_heap(begin(nodes), end(nodes), compare);
					}
			}
		}



		return path;
	}

};



int main(){
	graph g;
    g.add_vertex('A', {{'B', 7}, {'C', 8}});
    g.add_vertex('B', {{'A', 7}, {'F', 2}});
    g.add_vertex('C', {{'A', 8}, {'F', 6}, {'G', 4}});
    g.add_vertex('D', {{'F', 8}});
    g.add_vertex('E', {{'H', 1}});
    g.add_vertex('F', {{'B', 2}, {'C', 6}, {'D', 8}, {'G', 9}, {'H', 3}});
    g.add_vertex('G', {{'C', 4}, {'F', 9}});
    g.add_vertex('H', {{'E', 1}, {'F', 3}});
	vector<char> y = g.solve('A', 'H');
	for(auto a : y)
		cout<<a<<" ";
}
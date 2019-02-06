
#include <cstdlib>
#include <vector>
#include <iostream>
#include <array>
#include <string>

using namespace std;

static int maximum = 0;

int getHash(string key)
{
	int sum = key[0] - 'A' + 1;
	for (int c = 1; c < key.length(); c++)
	{
		if (c % 2)
			sum += (key[c] - 'A');
		else
			sum += ((key[c] - 'A') * 2);
	}
	if (sum > maximum)
		maximum = sum - 100;
	return sum - 100;
}

class HashEntry 
{
private:
      int key;
      string value;
public:
      HashEntry(int k=0, string v="") 
	  {
            key = k;
            value = v;
      }
      int getKey() 
	  {	
            return key;
      }
      string getValue() 
	  {
            return value;
      }
	  operator int() { return getKey(); }
};

class HashTable 
{
private:
      vector<HashEntry> table;
	  int collisions = 0;
public:
	HashTable(int s = 1) { table.resize(s); }
	int size() { return table.size(); }
	int getcollisions() { return collisions; }
	string get(string key) 
	{
		int hash = getHash(key);
		return table[hash].getValue();
	}
	HashEntry get(int index)
	{
		return table[index];
	}
	void put(string value) 
	{
		int hash = getHash(value);
		HashEntry entry(hash,value);
		int key = table[hash].getKey();
		if (key > 0)
			collisions++;
		table[hash] = entry;
	}     
	HashEntry operator [] (int index)
	{
		return get(index);
	}
};

const unsigned TABLE_SIZE = 0x0300;  

void main()
{
	array<string,117> elements = 
	{ 
		"Silver",
		"Aluminium",
		"Americium",
		"Argon",
		"Arsenic",
		"Astatine",
		"Gold",
		"Boron",
		"Barium",
		"Beryllium",
		"Bohrium",
		"Bismuth",
		"Berkelium",
		"Bromine",
		"Carbon",
		"Calcium",
		"Cadmium",
		"Cerium",
		"Californium",
		"Chlorine",
		"Curium",
		"Copernicium",
		"Cobalt",
		"Chromium",
		"Caesium",
		"Copper",
		"Dubnium",
		"Darmstadtium",
		"Dysprosium",
		"Erbium",
		"Einsteinium",
		"Europium",
		"Fluorine",
		"Iron",
		"Flerovium",
		"Fermium",
		"Francium",
		"Gallium",
		"Gadolinium",
		"Germanium",
		"Hydrogen",
		"Helium",
		"Hafnium",
		"Mercury",
		"Holmium",
		"Hassium",
		"Iodine",
		"Indium",
		"Iridium",
		"Potassium",
		"Krypton",
		"Lanthanum",
		"Lithium",
		"Lawrencium",
		"Lutetium",
		"Livermorium",
		"Moscovium",
		"Mendelevium",
		"Magnesium",
		"Manganese",
		"Molybdenum",
		"Meitnerium",
		"Nitrogen",
		"Sodium",
		"Niobium",
		"Neodymium",
		"Neon",
		"Nihonium",
		"Nickel",
		"Nobelium",
		"Neptunium",
		"Oxygen",
		"Oganesson",
		"Osmium",
		"Phosphorus",
		"Protactinium",
		"Lead",
		"Palladium",
		"Promethium",
		"Polonium",
		"Praseodymium",
		"Platinum",
		"Plutonium",
		"Radium",
		"Rubidium",
		"Rhenium",
		"Rutherfordium",
		"Roentgenium",
		"Rhodium",
		"Radon",
		"Ruthenium",
		"Sulfur",
		"Antimony",
		"Scandium",
		"Selenium",
		"Seaborgium",
		"Silicon",
		"Samarium",
		"Tin",
		"Strontium",
		"Tantalum",
		"Terbium",
		"Technetium",
		"Tellurium",
		"Thorium",
		"Titanium",
		"Thallium",
		"Thulium",
		"Tennessine",
		"Uranium",
		"Vanadium",
		"Tungsten",
		"Xenon",
		"Yttrium",
		"Ytterbium",
		"Zinc",
		"Zirconium"
	};

	HashTable htable(TABLE_SIZE);
	for (int x=0; x<elements.size(); x++)
		htable.put(elements[x]);
	int used = 0;
	for (int x = 0; x < htable.size(); x++)
	{
		HashEntry hentry = htable.get(x);
		if (hentry.getKey())
		{
			used++;
			if ( x % 2)
				cout << yellow << "\n[" << hentry.getKey() << "] " << '\t' << hentry.getValue();
			else
				cout << green << "\n[" << hentry.getKey() << "] " << '\t' << hentry.getValue();
		}
		else
			cout << blue << ".";
	}
	cout << red;
	cout << "\nMaximum = " << maximum << endl;
	cout << " % used = " << (float)used / (float)htable.size()*100.0 << endl;
	cout << " collisions = " << htable.getcollisions() << endl;
}
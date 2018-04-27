namespace java sc.leveldb
service KvService{
    string getValue(1: string key),
    list<string> mgetValues(1: list<string> keys),
    bool setValue(1: string key, 2: string value),
    bool msetValues(1: list<string> keys, 2: list<string> values)
}
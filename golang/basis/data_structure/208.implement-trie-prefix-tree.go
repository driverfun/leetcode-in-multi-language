package data_structure

type Trie struct {
	IsEnd    bool
	Children map[int32]*Trie
}

func ConstructorTrie() Trie {
	return Trie{
		Children: make(map[int32]*Trie),
	}
}

func (this *Trie) Insert(word string) {
	for _, ch := range []int32(word) {
		if this.Children[ch] == nil {
			t := ConstructorTrie()
			this.Children[ch] = &t
		}
		this = this.Children[ch]
	}
	this.IsEnd = true
}

func (this *Trie) Search(word string) bool {
	for _, ch := range []int32(word) {
		if this.Children[ch] == nil {
			return false
		}
		this = this.Children[ch]
	}
	return this.IsEnd
}

func (this *Trie) StartsWith(prefix string) bool {
	// 一个个 rune 查，如果 children 为空，则后半后半部分不在字典树
	for _, ch := range []int32(prefix) {
		if this.Children[ch] == nil {
			return false
		}
		this = this.Children[ch]
	}
	return true
}

// 先寻到 prefix 的最末，再把 prefix 加上所有的可能值给返回出来
func (this *Trie) Suggest(prefix string) []string {
	prefixBytes := []int32(prefix)
	for _, ch := range prefixBytes {
		this = this.Children[ch]
	}
	return dfs(this, prefixBytes, nil)
}

func dfs(this *Trie, prefix []int32, suggestions []string) []string {
	for ch, t := range this.Children {
		prefix := append(prefix, ch)
		if len(t.Children) == 0 {
			suggestions = append(suggestions, string(prefix))
		} else {
			suggestions = dfs(t, prefix, suggestions)
		}
	}
	return suggestions
}

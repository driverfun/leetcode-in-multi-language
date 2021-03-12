package data_structure

import (
	"fmt"
	"testing"
)

func TestConstructor(t *testing.T) {
	l := Constructor(2)
	l.Put(1, 1)
	l.Put(2, 2)
	fmt.Println(l.Get(1))
	l.Put(3, 3)
	fmt.Println(l.Get(2))
	l.Put(4, 4)
	fmt.Println(l.Get(1))
	fmt.Println(l.Get(3))
	fmt.Println(l.Get(4))
}

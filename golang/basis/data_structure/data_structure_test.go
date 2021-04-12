package data_structure

import (
	"fmt"
	"testing"

	"github.com/stretchr/testify/require"
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

func NewTestCase(count int) map[int]int {
	m := make(map[int]int, count)
	for i := 0; i < count; i++ {
		m[i] = i
	}
	return m
}

func TestNewMap(t *testing.T) {
	m := NewMap(677)
	testcase := NewTestCase(1024)
	for key, val := range testcase {
		m.Store(key, val)
	}
	for key, val := range testcase {
		require.Equal(t, val, m.Get(key))
	}
}

func BenchmarkNewMap(b *testing.B) {
	testcase := NewTestCase(100000)
	m := NewMap(677)
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		for key, val := range testcase {
			m.Store(key, val)
		}
		for key, val := range testcase {
			require.Equal(b, val, m.Get(key))
		}
	}
}

func BenchmarkNewMapV2(b *testing.B) {
	testcase := NewTestCase(100000)
	m := make(map[interface{}]interface{}, len(testcase))
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		for key, val := range testcase {
			m[key] = val
		}
		for key, val := range testcase {
			require.Equal(b, val, m[key])
		}
	}
}

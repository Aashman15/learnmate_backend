package com.aashman.learnmate.features.question;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import com.aashman.learnmate.dto.MessageDto;
import com.aashman.learnmate.entities.MyCollection;
import com.aashman.learnmate.entities.Question;
import com.aashman.learnmate.features.mycollection.MyCollectionRepository;
import com.aashman.learnmate.features.question.dto.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private MyCollectionRepository collectionRepository;

    @Mock
    private QuestionMapper questionMapper;

    @InjectMocks
    private QuestionService questionService;

    @Test
    void create_shouldSaveQuestionAndIncreaseCount() {
        QuestionCreateRequest request = new QuestionCreateRequest();
        request.setCollectionId(1L);

        MyCollection collection = new MyCollection();
        collection.setQuestionCount(2);

        Question question = new Question();
        Question saved = new Question();
        saved.setId(10L);

        QuestionDto dto = new QuestionDto();
        dto.setId(10L);

        when(collectionRepository.findByIdOrThrow(1L)).thenReturn(collection);
        when(questionMapper.mapCreateRequestToEntity(request)).thenReturn(question);
        when(questionRepository.save(question)).thenReturn(saved);
        when(questionMapper.mapEntityToBaseDto(saved)).thenReturn(dto);

        QuestionDto result = questionService.create(request);

        assertEquals(3, collection.getQuestionCount());
        assertEquals(10L, result.getId());
        verify(collectionRepository).save(collection);
    }

    @Test
    void create_shouldThrowIfCollectionNotFound() {
        QuestionCreateRequest request = new QuestionCreateRequest();
        request.setCollectionId(99L);

        when(collectionRepository.findByIdOrThrow(99L))
                .thenThrow(new RuntimeException("Not found"));

        assertThrows(RuntimeException.class, () -> questionService.create(request));
    }

    @Test
    void findAllByCollectionId_shouldReturnMappedList() {
        when(questionRepository.findAllByCollectionId(1L))
                .thenReturn(List.of(new Question(), new Question()));
        when(questionMapper.mapEntityToBaseDto(any()))
                .thenReturn(new QuestionDto());

        List<QuestionDto> result = questionService.findAllByCollectionId(1L);

        assertEquals(2, result.size());
    }

    @Test
    void findAllByCollectionId_shouldReturnEmptyList() {
        when(questionRepository.findAllByCollectionId(1L))
                .thenReturn(List.of());

        List<QuestionDto> result = questionService.findAllByCollectionId(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    void findById_shouldReturnDetailDto() {
        Question question = new Question();
        QuestionDetailDto dto = new QuestionDetailDto();

        when(questionRepository.findByIdOrThrow(1L)).thenReturn(question);
        when(questionMapper.mapEntityToDetailDto(question)).thenReturn(dto);

        QuestionDetailDto result = questionService.findById(1L);

        assertSame(dto, result);
    }

    @Test
    void findById_shouldThrowIfNotFound() {
        when(questionRepository.findByIdOrThrow(1L))
                .thenThrow(new RuntimeException("Not found"));

        assertThrows(RuntimeException.class, () -> questionService.findById(1L));
    }
    @Test
    void deleteById_shouldDeleteAndReturnMessage() {
        when(questionRepository.findByIdOrThrow(1L)).thenReturn(new Question());

        MessageDto result = questionService.deleteById(1L);

        verify(questionRepository).deleteById(1L);
        assertEquals("Question deleted successfully", result.getMessage());
    }

    @Test
    void deleteById_shouldThrowIfNotFound() {
        when(questionRepository.findByIdOrThrow(1L))
                .thenThrow(new RuntimeException("Not found"));

        assertThrows(RuntimeException.class, () -> questionService.deleteById(1L));
    }
    @Test
    void update_shouldMergeSaveAndReturnDto() {
        Question existing = new Question();
        QuestionUpdateRequest updateRequest = new QuestionUpdateRequest();
        Question updated = new Question();
        QuestionDto dto = new QuestionDto();

        when(questionRepository.findByIdOrThrow(1L)).thenReturn(existing);
        when(questionRepository.save(existing)).thenReturn(updated);
        when(questionMapper.mapEntityToBaseDto(updated)).thenReturn(dto);

        QuestionDto result = questionService.update(1L, updateRequest);

        verify(questionMapper).mergeUpdateRequestToEntity(updateRequest, existing);
        verify(questionRepository).save(existing);
        assertSame(dto, result);
    }

    @Test
    void update_shouldThrowIfNotFound() {
        when(questionRepository.findByIdOrThrow(1L))
                .thenThrow(new RuntimeException("Not found"));

        assertThrows(RuntimeException.class,
                () -> questionService.update(1L, new QuestionUpdateRequest()));
    }

    @Test
    void update_shouldFailIfMapperThrows() {
        Question existing = new Question();
        QuestionUpdateRequest request = new QuestionUpdateRequest();

        when(questionRepository.findByIdOrThrow(1L)).thenReturn(existing);
        doThrow(new RuntimeException("Merge failed"))
                .when(questionMapper).mergeUpdateRequestToEntity(request, existing);

        assertThrows(RuntimeException.class, () -> questionService.update(1L, request));
    }

    @Test
    void update_shouldFailIfSaveThrows() {
        Question existing = new Question();
        QuestionUpdateRequest request = new QuestionUpdateRequest();

        when(questionRepository.findByIdOrThrow(1L)).thenReturn(existing);
        when(questionRepository.save(existing))
                .thenThrow(new RuntimeException("DB error"));

        assertThrows(RuntimeException.class, () -> questionService.update(1L, request));
    }
}

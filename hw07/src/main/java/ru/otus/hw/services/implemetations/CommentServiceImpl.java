package ru.otus.hw.services.implemetations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.models.dto.CommentDto;
import ru.otus.hw.repositories.CommentRepository;
import ru.otus.hw.services.CommentService;
import ru.otus.hw.services.mappers.CommentMapper;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    @Override
    public Optional<CommentDto> findById(long id) {
        return commentRepository
            .findById(id)
            .map(commentMapper::toDto);
    }

    @Override
    public List<CommentDto> findAllForBook(long bookId) {
        return commentRepository
//            .findAllForBook(bookId)
            .findAllByBookId(bookId)
            .stream()
            .map(commentMapper::toDto)
            .toList();
    }

    @Override
    public CommentDto save(CommentDto commentDto) {
        ru.otus.hw.models.entity.Comment commentEntity = commentRepository.save(commentMapper.toEntity(commentDto));
        return commentMapper.toDto(commentEntity);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
}
